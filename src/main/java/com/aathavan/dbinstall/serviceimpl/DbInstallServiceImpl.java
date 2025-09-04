package com.aathavan.dbinstall.serviceimpl;

import com.aathavan.dbinstall.common.DbInstallConstant;
import com.aathavan.dbinstall.config.ConnectionConfig;
import com.aathavan.dbinstall.dao.DbInstallDao;
import com.aathavan.dbinstall.model.MySqlColumns;
import com.aathavan.dbinstall.model.MySqlTable;
import com.aathavan.dbinstall.service.DbInstallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class DbInstallServiceImpl implements DbInstallService {


    @Autowired
    private DbInstallDao dbInstallDao;


    @Override
    public void installTable(List<MySqlTable> tableName, String dbName) throws Exception {
        List<String> installQuery = new LinkedList<>();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DbInstallConstant.getDataSource());
        boolean isFreshDb = !dbInstallDao.checkExist(prepareStringForDbExist(dbName), jdbcTemplate);
        if (isFreshDb) {
            dbInstallDao.executeQuery("create database " + dbName, jdbcTemplate);
        }
        JdbcTemplate jdbcTemplateForDb = new JdbcTemplate(new ConnectionConfig().getDbDataSource(dbName));


        for (MySqlTable mySqlTable : tableName) {
            if (isFreshDb || !prepareStringForTableExist(mySqlTable.getTablename(), jdbcTemplateForDb)) {

                dbInstallDao.executeQuery(mySqlTable.getTable(), jdbcTemplateForDb);
            } else {
                mySqlAlterColumnProcess(mySqlTable, dbName, jdbcTemplateForDb);
            }
        }
    }

    private boolean prepareStringForTableExist(String tableName, JdbcTemplate jdbcTemplate) throws Exception {
        return dbInstallDao.checkExist("show tables like '" + tableName + "' ", jdbcTemplate);
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
                String alterQuery = "alter table " + mySqlTable.getTablename() + " add column " + mySqlColumns.getColumn();
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
