package com.aathavan.dbinstall.dbinstalldatapreparation;

import com.aathavan.dbinstall.dao.DbInstallDao;
import com.aathavan.dbinstall.form.FormMain;
import com.aathavan.dbinstall.model.MySqlTable;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ProcedureProcess {
    private final Logger logger = Logger.getLogger(ProcedureProcess.class);
    @Autowired
    private DbInstallDao dbInstallDao;

    public void procedureProcess(List<MySqlTable> lstTables, JdbcTemplate jdbcTemplateForDb, String dbName) {
        try {

            dropOldProcedure(jdbcTemplateForDb, dbName);
            createProcedure(lstTables, jdbcTemplateForDb);


        } catch (Exception e) {
            logger.error(e.getMessage());
            FormMain.setTextArea(e.getMessage());
        }
    }

    private void createProcedure(List<MySqlTable> lstTables, JdbcTemplate jdbcTemplateForDb) {
        for (MySqlTable mySqlTable : lstTables) {
            if (mySqlTable.isProcedureReq()) {
                logger.error("Creating Procedure spsave" + mySqlTable.getTablename());
                FormMain.setTextArea("Creating Procedure spsave" + mySqlTable.getTablename());
                dbInstallDao.executeQuery(mySqlTable.getProcedure(), jdbcTemplateForDb);
            }
        }
    }

    private void dropOldProcedure(JdbcTemplate jdbcTemplateForDb, String dbName) {
        List<Map<String, Object>> lstDropProcedure = dbInstallDao.getData("SHOW PROCEDURE STATUS  WHERE Db = '" + dbName + "'", jdbcTemplateForDb);
        for (Map<String, Object> map : lstDropProcedure) {
            FormMain.setTextArea("Droping Procedure  " + map.get("Name"));
            dbInstallDao.executeQuery("DROP PROCEDURE " + map.get("Name"), jdbcTemplateForDb);
        }
    }
}
