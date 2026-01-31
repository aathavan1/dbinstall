package com.aathavan.dbinstall.model;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

public class MySqlTable {

    @Getter
    private String tablename;
    private String constrains = null;
    @Setter
    @Getter
    private boolean master;
    @Getter
    private boolean procedureReq = false;
    @Getter
    @Setter
    private String dbname;
    @Getter
    private List<MySqlColumns> lstColumns = new LinkedList<>();

    public MySqlTable(String tableName) {
        this.tablename = tableName;
    }

    public MySqlTable(String tableName, boolean procedure) {
        this.tablename = tableName;
        this.procedureReq = procedure;
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

    public String getProcedure() {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE PROCEDURE spsave").append(tablename).append("( \n");
        for (MySqlColumns mySqlColumns : lstColumns) {
            sb.append("IN p_").append(mySqlColumns.getColumnForSp()).append(" ,\n");
        }
        sb = new StringBuilder(sb.toString().trim());
        sb = new StringBuilder(sb.substring(0, sb.toString().length() - 1));
        sb.append(") ");
        sb.append("BEGIN \n INSERT INTO ").append(tablename).append(" (");
        for (MySqlColumns mySqlColumns : lstColumns) {
            sb.append(mySqlColumns.getColumnname()).append(" ,\n");
        }
        sb = new StringBuilder(sb.toString().trim());
        sb = new StringBuilder(sb.substring(0, sb.toString().length() - 1));
        sb.append(") values (");
        for (MySqlColumns mySqlColumns : lstColumns) {
            sb.append(" p_").append(mySqlColumns.getColumnname()).append(" ,\n");
        }
        sb = new StringBuilder(sb.toString().trim());
        sb = new StringBuilder(sb.substring(0, sb.toString().length() - 1));
        sb.append(" ); END \n");
//        sb.append(" DELIMITER ;");


        return sb.toString();
    }


}
