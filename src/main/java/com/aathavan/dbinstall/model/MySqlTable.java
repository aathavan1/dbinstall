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

    public String getConstrains() {
        return constrains;
    }

    public boolean isProcedure() {
        return procedure;
    }

    public List<MySqlColumns> getLstColumns() {
        return lstColumns;
    }

}
