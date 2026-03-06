package com.aathavan.dbinstall.model;

import com.aathavan.dbinstall.common.CommonEnum;
import com.aathavan.dbinstall.dao.DbInstallDao;
import com.aathavan.dbinstall.daoimpl.DbInstallDaoImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;
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
    private String tablePrefix = null;
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

    public void setDefaultColumn() {
        if (master)
            lstColumns.addAll(new LinkedList<>(Arrays.asList(
                    new MySqlColumns("active", CommonEnum.DATATYPE.VARCHAR, 1, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, "Y"),
                    new MySqlColumns("createddate", CommonEnum.DATATYPE.DATE, CommonEnum.NULLABLE.YES, CommonEnum.UNIQUEKEY.NO, "YES"),
                    new MySqlColumns("createdtime", CommonEnum.DATATYPE.DATE, CommonEnum.NULLABLE.YES, CommonEnum.UNIQUEKEY.NO, "YES"),
                    new MySqlColumns("createdby", CommonEnum.DATATYPE.INT, CommonEnum.NULLABLE.YES, CommonEnum.UNIQUEKEY.NO, "0"))));
        else {
            lstColumns.addAll(new LinkedList<>(Arrays.asList(
                    new MySqlColumns("createddate", CommonEnum.DATATYPE.DATE, CommonEnum.NULLABLE.YES, CommonEnum.UNIQUEKEY.NO, "YES"),
                    new MySqlColumns("createdtime", CommonEnum.DATATYPE.DATE, CommonEnum.NULLABLE.YES, CommonEnum.UNIQUEKEY.NO, "YES"),
                    new MySqlColumns("createdby", CommonEnum.DATATYPE.INT, CommonEnum.NULLABLE.YES, CommonEnum.UNIQUEKEY.NO, "0"))));

        }
    }

    public String getTable() {

        StringBuilder sb = new StringBuilder();
        if (tablePrefix == null) {
            tablePrefix = new DbInstallDaoImpl().getTablePrefix(tablename);
        }
        setDefaultColumn();
        sb.append("CREATE TABLE ").append(tablename).append(" (");
        if (tablePrefix != null)
            sb.append(tablePrefix).append("id INT PRIMARY KEY DEFAULT 0 ,\n");
        for (MySqlColumns mySqlColumns : lstColumns) {
            sb.append(tablePrefix).append(mySqlColumns.getColumn(false)).append(" ,\n");
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
