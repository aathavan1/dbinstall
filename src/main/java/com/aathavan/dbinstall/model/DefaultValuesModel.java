package com.aathavan.dbinstall.model;


import com.aathavan.dbinstall.daoimpl.DbInstallDaoImpl;
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
    @Getter
    private boolean update = false;
    @Setter
    private String tablePrefix;

    List<String> lstColumnName = new LinkedList<>();
    private List<Map<String, Object>> lstColumnValues = new LinkedList<>();

    public DefaultValuesModel(String tablename, String dbname, boolean update) {
        this.tablename = tablename;
        this.update = update;
        this.dbname = dbname;
        tablePrefix = new DbInstallDaoImpl().getTablePrefix(tablename);
    }

    public void setColumnName(List<String> lstColumnName) {
        lstColumnName.addFirst("id");
        this.lstColumnName = lstColumnName;
    }

    public void setColumnValues(List<String> columnvalues) {
        try {
            Map<String, Object> map = new LinkedHashMap<>();
            if (columnvalues.size() + 1 != lstColumnName.size()) {

                logger.error("Invalid Column Count For " + tablename);
                FormMain.setTextArea("Invalid Column Count For " + tablename);
            }

            for (String column : lstColumnName) {
                if (lstColumnName.indexOf(column) != 0)
                    map.put(column, columnvalues.get(lstColumnName.indexOf(column) - 1));
            }
            map.put("id", lstColumnValues.size() + 1);
            lstColumnValues.add(map);
        } catch (Exception e) {
            logger.error(e.getMessage());
            FormMain.setTextArea(e.getMessage());
        }
    }

    public String getInsertQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(dbname).append(".").append(tablename).append(" (");
        for (String colName : lstColumnName) {
            sb.append(tablePrefix).append(colName).append(" ,");
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
            if (!colName.equalsIgnoreCase(tablePrefix + "id"))
                sb.append(tablePrefix).append(colName).append(" = :").append(colName).append(" ,");
        }
        sb = new StringBuilder(sb.substring(0, sb.toString().length() - 1));
        sb.append(" WHERE ").append(tablePrefix).append("id").append(" = :").append("id").append("; ");
        return sb.toString();
    }

    public List<Map<String, Object>> getData() {
        return lstColumnValues;
    }


}
