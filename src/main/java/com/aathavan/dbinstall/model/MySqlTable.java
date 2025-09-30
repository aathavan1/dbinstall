package com.aathavan.dbinstall.model;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

public class MySqlTable {

    @Getter
    private String tablename;
    private String constrains = null;
    private boolean procedure = false;
    @Getter
    private List<MySqlColumns> lstColumns = new LinkedList<>();

    public MySqlTable(String tableName) {
        this.tablename = tableName;
    }

    public MySqlTable(String tableName, boolean procedure) {
        this.tablename = tableName;
        this.procedure = procedure;
    }


    public void addColumns(MySqlColumns mySqlColumns) {
        lstColumns.add(mySqlColumns);
    }

    public void addConstrains(String constrain) {
        this.constrains = constrain;
    }


    public String getTable() {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ").append(tablename).append(" (");
        for (MySqlColumns mySqlColumns : lstColumns) {
            sb.append(mySqlColumns.getColumn()).append(" ,\n");
        }
        if (constrains != null && !constrains.isEmpty()) {
            sb.append(constrains).append(")");
            return sb.toString();
        }
        String tableCreationQuery = sb.toString().trim();
        return tableCreationQuery.trim().substring(0, tableCreationQuery.length() - 1) + ")";
    }

}
