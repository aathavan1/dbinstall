package com.aathavan.dbinstall.dbinstalldatapreparation;

import com.aathavan.dbinstall.common.DbInstallConstant;
import com.aathavan.dbinstall.dao.DbInstallDao;
import com.aathavan.dbinstall.form.FormMain;
import com.aathavan.dbinstall.model.DefaultValuesModel;
import com.aathavan.dbinstall.model.MySqlColumns;
import com.aathavan.dbinstall.model.MySqlTable;
import com.aathavan.dbinstall.serviceimpl.DbInstallServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TableAlterProcess {

    private final Logger logger = Logger.getLogger(TableAlterProcess.class);
    @Autowired
    private DbInstallDao dbInstallDao;

    public void mySqlAlterColumnProcess(MySqlTable mySqlTable, String dbName, JdbcTemplate jdbcTemplate) throws Exception {
        for (MySqlColumns mySqlColumns : mySqlTable.getLstColumns()) {
            if (!checkColumnExist(mySqlTable.getTablename(), mySqlColumns.getColumnname(), jdbcTemplate, dbName)) {
                String alterQuery = "ALTER TABLE " + mySqlTable.getTablename() + " ADD COLUMN " + mySqlColumns.getColumn();
                dbInstallDao.executeQuery(alterQuery, jdbcTemplate);
            }
        }
//        else{
//            if(checkAlterColumn()){
//
//            }
//        }
    }

    private boolean checkColumnExist(String tableName, String columnName, JdbcTemplate jdbcTemplate, String dbName) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT TABLE_SCHEMA FROM INFORMATION_SCHEMA.COLUMNS \n");
        sb.append("WHERE TABLE_SCHEMA = '").append(dbName).append("' ");
        sb.append(" AND TABLE_NAME = '").append(tableName).append("' ");
        sb.append("AND COLUMN_NAME = '").append(columnName).append("' ");

        return dbInstallDao.checkExist(sb.toString(), jdbcTemplate);
    }

    public String prepareStringForDbExist(String dbName) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT SCHEMA_NAME \n");
        sb.append("FROM information_schema.schemata \n");
        sb.append("WHERE SCHEMA_NAME = '").append(dbName).append("'");

        return sb.toString();
    }

    public String prepareStringForColumnDataTypes() {
        StringBuilder sb = new StringBuilder();

        sb.append("select data_type,character_maximum_length,numeric_precision,numeric_scale,datetime_precision \n");
        sb.append(" from information_schema.columns\n");
        sb.append(" where table_name = 'product'\n");
        sb.append("and column_name = 'mrprate' \n");
        sb.append("and table_schema = 'aatamaster' \n");


        return sb.toString();
    }

    public void isInsertTableValue(DefaultValuesModel defaultValuesModel) {
        try {

            NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(DbInstallConstant.getDataSource());

            List<Map<String, Object>> lstTableValues = dbInstallDao.checkDataForDefaultValues(defaultValuesModel);


            List<Map<String, Object>> lstTableValuesForUpdate = defaultValuesModel.getData().stream().filter(
                    defVal -> lstTableValues.stream().anyMatch(tblVal -> String.valueOf(tblVal.get(defaultValuesModel.getPrimaryColName()))
                            .equalsIgnoreCase(String.valueOf(defVal.get(defaultValuesModel.getPrimaryColName()))))).toList();

            List<Map<String, Object>> lstTableValuesForInsert = defaultValuesModel.getData().stream().filter(
                    defVal -> lstTableValues.stream().noneMatch(tblVal -> String.valueOf(tblVal.get(defaultValuesModel.getPrimaryColName()))
                            .equalsIgnoreCase(String.valueOf(defVal.get(defaultValuesModel.getPrimaryColName()))))).toList();


            if (!lstTableValuesForInsert.isEmpty()) {
                jdbcTemplate.batchUpdate(defaultValuesModel.getInsertQuery(), lstTableValuesForInsert.toArray(new Map[0]));
            }
            if (!lstTableValuesForUpdate.isEmpty()) {
                jdbcTemplate.batchUpdate(defaultValuesModel.getUpdateQuery(), lstTableValuesForUpdate.toArray(new Map[0]));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            FormMain.setTextArea(e.getMessage());
        }
    }

}
