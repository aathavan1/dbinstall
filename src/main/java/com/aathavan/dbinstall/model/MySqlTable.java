package com.aathavan.dbinstall.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MySqlTable {


    private String tablename;
    private String constrains = null;
    private boolean procedure = false;
    private List<MySqlColumns> lstColumns = new LinkedList<>();


    public void addColumns(MySqlColumns mySqlColumns) {
        lstColumns.add(mySqlColumns);
    }

    public void setTableName(String tableName, boolean procedure) {
        this.tablename = tableName;
    }

    public void addConstrains(String constrain) {
        this.constrains = constrain;
    }

    public String getTablename() {
        return tablename;
    }


    public String getTable() {
        StringBuilder sb = new StringBuilder();
        sb.append("create table ").append(tablename).append(" (");
        for (MySqlColumns mySqlColumns : lstColumns) {
            sb.append(mySqlColumns.getColumn()).append(" ,");
        }
        return sb.substring(0, sb.toString().length() - 1) + ")";
    }

}
