package com.aathavan.dbinstall.model;


import com.aathavan.dbinstall.form.FormMain;
import com.aathavan.dbinstall.serviceimpl.DbInstallServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DefaultValuesModel {

    private final Logger logger = Logger.getLogger(DefaultValuesModel.class);
    @Getter
    private String tablename;
    @Getter
    private String dbname;
    @Setter
    @Getter
    private String primaryColName;
    List<String> lstColumnName = new LinkedList<>();
    private List<Map<String, Object>> lstColumnValues = new LinkedList<>();

    public DefaultValuesModel(String tablename, String dbname) {
        this.tablename = tablename;
        this.dbname = dbname;
    }

    public void setColumnName(List<String> lstColumnName) {
        this.lstColumnName = lstColumnName;
    }

    public void setColumnValues(List<String> columnvalues) {
        Map<String, Object> map = new LinkedHashMap<>();
        if (columnvalues.size() != lstColumnName.size()) {

            logger.error("Invalid Column Count For " + tablename);
            FormMain.setTextArea("Invalid Column Count For " + tablename);
        }

        for (String column : lstColumnName) {
            map.put(column, columnvalues.get(lstColumnName.indexOf(column)));
        }
        lstColumnValues.add(map);
    }

    public String getInsertQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(dbname).append(".").append(tablename).append(" (");
        for (String colName : lstColumnName) {
            sb.append(colName).append(" ,");
        }
        sb = new StringBuilder(sb.substring(0, sb.toString().length() - 1));
        sb.append(" ) VALUES (");
        for (String colName : lstColumnName) {
            sb.append(":").append(colName).append(" ,");
        }
        sb = new StringBuilder(sb.substring(0, sb.toString().length() - 1));
        sb.append(");");
        return sb.toString();
    }

    public String getUpdateQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ").append(dbname).append(".").append(tablename).append(" set ");
        for (String colName : lstColumnName) {
            if (!colName.equalsIgnoreCase(primaryColName))
                sb.append(colName).append(" = :").append(colName).append(" ,");
        }
        sb = new StringBuilder(sb.substring(0, sb.toString().length() - 1));
        sb.append(" WHERE ").append(primaryColName).append(" = :").append(primaryColName).append("; ");
        return sb.toString();
    }

    public List<Map<String, Object>> getData() {
        return lstColumnValues;
    }


}
