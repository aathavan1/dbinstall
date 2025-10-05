package com.aathavan.dbinstall.serviceimpl;

import com.aathavan.dbinstall.common.DbInstallConstant;
import com.aathavan.dbinstall.config.ConnectionConfig;
import com.aathavan.dbinstall.dao.DbInstallDao;
import com.aathavan.dbinstall.dbinstalldatapreparation.ProcedureProcess;
import com.aathavan.dbinstall.dbinstalldatapreparation.TableAlterProcess;
import com.aathavan.dbinstall.form.FormMain;
import com.aathavan.dbinstall.model.DefaultValuesModel;
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
    @Autowired
    private TableAlterProcess tableAlterProcess;
    @Autowired
    private ProcedureProcess procedureProcess;


    @Override
    public void installTable(List<MySqlTable> lstTables, String dbName) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(DbInstallConstant.getDataSource());
            boolean isFreshDb = !dbInstallDao.checkExist(tableAlterProcess.prepareStringForDbExist(dbName), jdbcTemplate);
//            ;

            if (isFreshDb) {
                FormMain.setTextArea("Creating DataBase " + dbName);
                dbInstallDao.executeQuery("CREATE DATABASE " + dbName, jdbcTemplate);
            }
            JdbcTemplate jdbcTemplateForDb = new JdbcTemplate(new ConnectionConfig().getDbDataSource(dbName));

            List<MySqlTable> lstAlterTables = new LinkedList<>();
            List<MySqlTable> lstInstallTables = new LinkedList<>();

            for (MySqlTable mySqlTable : lstTables) {
                if (isFreshDb || !prepareStringForTableExist(mySqlTable.getTablename(), jdbcTemplateForDb))
                    lstInstallTables.add(mySqlTable);
                else
                    lstAlterTables.add(mySqlTable);
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
                tableAlterProcess.mySqlAlterColumnProcess(mySqlTable, dbName, jdbcTemplateForDb);
            }
            FormMain.setTextArea("Alter Table Process Ends");

            FormMain.setTextArea("Procedure Creation Process Starts");

            procedureProcess.procedureProcess(lstTables, jdbcTemplateForDb, dbName);

            FormMain.setTextArea("Procedure Creation Process Ends");

        } catch (Exception e) {
            logger.error(e.getMessage());
            FormMain.setTextArea(e.getMessage());
        }

    }

    @Override
    public void defaultValues(List<Object> objects) {
        for (Object table : objects) {
            try {
                DefaultValuesModel defaultValuesModel = (DefaultValuesModel) table;
                tableAlterProcess.isInsertTableValue(defaultValuesModel);
            } catch (Exception e) {
                logger.error(e.getMessage());
                FormMain.setTextArea(e.getMessage());
            }
        }
    }

    private boolean prepareStringForTableExist(String tableName, JdbcTemplate jdbcTemplate) throws Exception {
        return dbInstallDao.checkExist("SHOW TABLES LIKE '" + tableName + "' ", jdbcTemplate);
    }
}
