package com.aathavan.dbinstall.install;

import com.aathavan.dbinstall.common.CommonEnum;
import com.aathavan.dbinstall.common.DbInstallCommon;
import com.aathavan.dbinstall.model.MySqlColumns;
import com.aathavan.dbinstall.model.MySqlTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class MasterTable {

    @Autowired
    private DbInstallCommon dbInstallCommon;

    public List<Object> preparePreTables() {
        List<Object> lstQuery = new LinkedList<>();
        lstQuery.add(getPreTableCreateQuery());
        lstQuery.addAll(getPreTableDataQuery());
        return lstQuery;
    }

    private String getPreTableCreateQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("create table pretable (tablename varchar(50), \n");
        sb.append("prefix varchar(4) default null, \n");
        sb.append("tablenature varchar(4), \n");
        sb.append("tablecode int primary key default 0, \n");
        sb.append("createddate date default null, \n");
        sb.append("createdtime time default null) \n");
        return sb.toString();
    }

    private List<Map<String, Object>> getPreTableDataQuery() {
        List<String> lstQuery = new LinkedList<>();
        lstQuery.add("'filemain', 'fil','mast','" + (1) + "'");
        lstQuery.add("'operator', 'ope','mast','" + (lstQuery.size() + 1) + "'");
        lstQuery.add("'product', 'pro','mast','" + (lstQuery.size() + 1) + "'");
        lstQuery.add("'category', 'cat','mast','" + (lstQuery.size() + 1) + "'");
        lstQuery.add("'computer', 'com','mast','" + (lstQuery.size() + 1) + "'");
        lstQuery.add("'employee', 'emp','mast','" + (lstQuery.size() + 1) + "'");
        String insertQuery = "insert into pretable (tablename ,prefix, tablenature ,tablecode ,createddate,createdtime) values (";
        String currentDateAndTime = ",'" + dbInstallCommon.getCurrentDate() + "','" + dbInstallCommon.getCurrentTime() + "')";

        List<Map<String, Object>> lstFinalQuery = new ArrayList<>();

        for (String query : lstQuery) {
            Map<String, Object> map = new HashMap<>();
            map.put("primarykey", String.valueOf(lstQuery.indexOf(query) + 1));
            map.put("query", insertQuery + query + currentDateAndTime);
            lstFinalQuery.add(map);
        }
        return lstFinalQuery;
    }

    public MySqlTable insertFileMainTable() {
        MySqlTable mySqlTable = new MySqlTable("filemain");
        mySqlTable.addColumns(new MySqlColumns("compcode", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.YES, null));
        mySqlTable.addColumns(new MySqlColumns("masterdbname", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("trandbname", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("finyear", CommonEnum.DATATYPE.VARCHAR, 5, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, null));

        return mySqlTable;
    }

    public MySqlTable insertOperatorTable() {
        MySqlTable mySqlTable = new MySqlTable("operator");
        mySqlTable.setMaster(true);
        mySqlTable.addColumns(new MySqlColumns("opercode", CommonEnum.DATATYPE.INT, CommonEnum.NULLABLE.NO, CommonEnum.PRIMARYKEY.YES));
        mySqlTable.addColumns(new MySqlColumns("opername", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, null));
//        mySqlTable.addColumns(new MySqlColumns("authtoken", CommonEnum.DATATYPE.VARCHAR, 300, CommonEnum.NULLABLE.YES, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("password", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("operlevel", CommonEnum.DATATYPE.VARCHAR, 1, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, "Y"));

        mySqlTable.addColumns(new MySqlColumns("active", CommonEnum.DATATYPE.VARCHAR, 1, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, "Y"));
        mySqlTable.addColumns(new MySqlColumns("createddate", CommonEnum.DATATYPE.DATE, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, "YES"));

        return mySqlTable;
    }

    public MySqlTable insertProductTable() {
        MySqlTable mySqlTable = new MySqlTable("product", true);
        mySqlTable.setMaster(true);
        mySqlTable.addColumns(new MySqlColumns("productcode", CommonEnum.DATATYPE.INT, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("productname", CommonEnum.DATATYPE.VARCHAR, 29, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("categorycode", CommonEnum.DATATYPE.INT, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("image", CommonEnum.DATATYPE.LONGBLOB, CommonEnum.NULLABLE.YES, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("description", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("mrprate", CommonEnum.DATATYPE.DECIMAL, 16, 2, CommonEnum.NULLABLE.NO));
        mySqlTable.addColumns(new MySqlColumns("active", CommonEnum.DATATYPE.VARCHAR, 1, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, "Y"));
        mySqlTable.addColumns(new MySqlColumns("createddate", CommonEnum.DATATYPE.DATE, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, null));

        return mySqlTable;
    }

    public MySqlTable insertCategoryTable() {
        MySqlTable mySqlTable = new MySqlTable("category");
        mySqlTable.setMaster(true);
        mySqlTable.addColumns(new MySqlColumns("catcode", CommonEnum.DATATYPE.INT, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("catname", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("mrprate", CommonEnum.DATATYPE.DECIMAL, 15, 3, CommonEnum.NULLABLE.NO));
        mySqlTable.addColumns(new MySqlColumns("active", CommonEnum.DATATYPE.VARCHAR, 1, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, "Y"));
        mySqlTable.addColumns(new MySqlColumns("createddate", CommonEnum.DATATYPE.DATE, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, "YES"));
        return mySqlTable;
    }


    public MySqlTable insertComputerTable() {
        MySqlTable mySqlTable = new MySqlTable("computer");
        mySqlTable.setMaster(true);
        mySqlTable.addColumns(new MySqlColumns("loginoperator", CommonEnum.DATATYPE.INT, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, "0"));
        mySqlTable.addColumns(new MySqlColumns("ipaddress", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("ipid", CommonEnum.DATATYPE.DECIMAL, 15, 3, CommonEnum.NULLABLE.NO));
        mySqlTable.addColumns(new MySqlColumns("active", CommonEnum.DATATYPE.VARCHAR, 1, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, "Y"));
        mySqlTable.addColumns(new MySqlColumns("createddate", CommonEnum.DATATYPE.DATE, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, "YES"));
        return mySqlTable;
    }

    public MySqlTable insertEmployeeTable() {
        MySqlTable mySqlTable = new MySqlTable("employee");
        mySqlTable.setMaster(true);
        mySqlTable.addColumns(new MySqlColumns("opercode", CommonEnum.DATATYPE.INT, CommonEnum.NULLABLE.NO, CommonEnum.PRIMARYKEY.YES));
        mySqlTable.addColumns(new MySqlColumns("empname", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("username", CommonEnum.DATATYPE.VARCHAR, 20, CommonEnum.NULLABLE.YES, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("email", CommonEnum.DATATYPE.VARCHAR, 50, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("active", CommonEnum.DATATYPE.VARCHAR, 1, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, "Y"));
        mySqlTable.addColumns(new MySqlColumns("createddate", CommonEnum.DATATYPE.DATE, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, "YES"));

        return mySqlTable;
    }

}
