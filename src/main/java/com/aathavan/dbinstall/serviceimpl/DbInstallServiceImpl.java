package com.aathavan.dbinstall.serviceimpl;

import com.aathavan.dbinstall.common.DbInstallConstant;
import com.aathavan.dbinstall.config.ConnectionConfig;
import com.aathavan.dbinstall.dao.DbInstallDao;
import com.aathavan.dbinstall.form.FormMain;
import com.aathavan.dbinstall.model.MySqlColumns;
import com.aathavan.dbinstall.model.MySqlTable;
import com.aathavan.dbinstall.service.DbInstallService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class DbInstallServiceImpl implements DbInstallService {

    private final Logger logger = Logger.getLogger(DbInstallServiceImpl.class);

    @Autowired
    private DbInstallDao dbInstallDao;


    @Override
    public void installTable(List<MySqlTable> lstMasterTables, String dbName) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(DbInstallConstant.getDataSource());
            boolean isFreshDb = !dbInstallDao.checkExist(prepareStringForDbExist(dbName), jdbcTemplate);
            if (isFreshDb) {
                FormMain.setTextArea("Creating DataBase " + dbName);
                dbInstallDao.executeQuery("CREATE DATABASE " + dbName, jdbcTemplate);
            }
            JdbcTemplate jdbcTemplateForDb = new JdbcTemplate(new ConnectionConfig().getDbDataSource(dbName));

            List<MySqlTable> lstAlterTables = new LinkedList<>();
            List<MySqlTable> lstInstallTables = new LinkedList<>();

            for (MySqlTable mySqlTable : lstMasterTables) {
                if (isFreshDb || !prepareStringForTableExist(mySqlTable.getTablename(), jdbcTemplateForDb)) {
                    lstInstallTables.add(mySqlTable);
                } else {
                    lstAlterTables.add(mySqlTable);
                }
            }


            FormMain.setTextArea("Creating Missing Table Process Starts");
            for (MySqlTable mySqlTable : lstInstallTables) {
                FormMain.getjProgressBar().setValue((lstInstallTables.indexOf(mySqlTable) * 100) / lstInstallTables.size());
                FormMain.setTextArea("Creating Table " + mySqlTable.getTablename());
                dbInstallDao.executeQuery(mySqlTable.getTable(), jdbcTemplateForDb);
            }
            FormMain.setTextArea("Creating Missing Table Process Ends");

            FormMain.setTextArea("Alter Table Process Starts");
            for (MySqlTable mySqlTable : lstAlterTables) {
                FormMain.getjProgressBar().setValue((lstAlterTables.indexOf(mySqlTable) * 100) / lstAlterTables.size());
                FormMain.setTextArea("Altering Table " + mySqlTable.getTablename());
                mySqlAlterColumnProcess(mySqlTable, dbName, jdbcTemplateForDb);
            }
            FormMain.setTextArea("Alter Table Process Ends");
        } catch (Exception e) {
            logger.error(e.getMessage());
            FormMain.setTextArea(e.getMessage());
        }

    }

    private boolean prepareStringForTableExist(String tableName, JdbcTemplate jdbcTemplate) throws Exception {
        return dbInstallDao.checkExist("SHOW TABLES LIKE '" + tableName + "' ", jdbcTemplate);
    }

    private String prepareStringForDbExist(String dbName) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT SCHEMA_NAME \n");
        sb.append("FROM information_schema.schemata \n");
        sb.append("WHERE SCHEMA_NAME = '").append(dbName).append("'");

        return sb.toString();
    }


    private void mySqlAlterColumnProcess(MySqlTable mySqlTable, String dbName, JdbcTemplate jdbcTemplate) throws Exception {
        for (MySqlColumns mySqlColumns : mySqlTable.getLstColumns()) {
            if (!checkColumnExist(mySqlTable.getTablename(), mySqlColumns.getColumnname(), jdbcTemplate, dbName)) {
                String alterQuery = "ALTER TABLE " + mySqlTable.getTablename() + " ADD COLUMN " + mySqlColumns.getColumn();
                dbInstallDao.executeQuery(alterQuery, jdbcTemplate);
            }
        }

    }

    private boolean checkColumnExist(String tableName, String columnName, JdbcTemplate jdbcTemplate, String dbName) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT TABLE_SCHEMA FROM INFORMATION_SCHEMA.COLUMNS \n");
        sb.append("WHERE TABLE_SCHEMA = '").append(dbName).append("' ");
        sb.append(" AND TABLE_NAME = '").append(tableName).append("' ");
        sb.append("AND COLUMN_NAME = '").append(columnName).append("' ");

        return dbInstallDao.checkExist(sb.toString(), jdbcTemplate);
    }


}
