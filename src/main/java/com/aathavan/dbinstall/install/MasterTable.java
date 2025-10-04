package com.aathavan.dbinstall.install;

import com.aathavan.dbinstall.common.CommonEnum;
import com.aathavan.dbinstall.model.MySqlColumns;
import com.aathavan.dbinstall.model.MySqlTable;
import org.springframework.stereotype.Component;

import java.time.Year;

@Component
public class MasterTable {

    public MySqlTable insertFileMainTable() {
        MySqlTable mySqlTable = new MySqlTable("filemain");
        mySqlTable.addColumns(new MySqlColumns("companycode", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.YES, null));
        mySqlTable.addColumns(new MySqlColumns("masterdbname", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("trandbname", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("financialyear", CommonEnum.DATATYPE.VARCHAR, 5, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, null));

        return mySqlTable;
    }

    public MySqlTable insertOperatorTable() {
        MySqlTable mySqlTable = new MySqlTable("operator");
        mySqlTable.addColumns(new MySqlColumns("opercode", CommonEnum.DATATYPE.INT, CommonEnum.NULLABLE.NO, CommonEnum.PRIMARYKEY.YES));
        mySqlTable.addColumns(new MySqlColumns("opername", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("authtoken", CommonEnum.DATATYPE.VARCHAR, 100, CommonEnum.NULLABLE.YES, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("password", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("active", CommonEnum.DATATYPE.VARCHAR, 1, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, "Y"));
        mySqlTable.addColumns(new MySqlColumns("createddate", CommonEnum.DATATYPE.DATE, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, "YES"));

        return mySqlTable;
    }

    public MySqlTable insertProductTable() {
        MySqlTable mySqlTable = new MySqlTable("product", true);
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
        mySqlTable.addColumns(new MySqlColumns("catcode", CommonEnum.DATATYPE.INT, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("catname", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("mrprate", CommonEnum.DATATYPE.DECIMAL, 15, 3, CommonEnum.NULLABLE.NO));
        mySqlTable.addColumns(new MySqlColumns("active", CommonEnum.DATATYPE.VARCHAR, 1, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, "Y"));
        mySqlTable.addColumns(new MySqlColumns("createddate", CommonEnum.DATATYPE.DATE, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, "YES"));
        return mySqlTable;
    }


    public MySqlTable insertComputerTable() {
        MySqlTable mySqlTable = new MySqlTable("computer");
        mySqlTable.addColumns(new MySqlColumns("loginoperator", CommonEnum.DATATYPE.INT, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, "0"));
        mySqlTable.addColumns(new MySqlColumns("ipaddress", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("ipid", CommonEnum.DATATYPE.DECIMAL, 15, 3, CommonEnum.NULLABLE.NO));
        mySqlTable.addColumns(new MySqlColumns("active", CommonEnum.DATATYPE.VARCHAR, 1, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, "Y"));
        mySqlTable.addColumns(new MySqlColumns("createddate", CommonEnum.DATATYPE.DATE, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, "YES"));
        return mySqlTable;
    }

    public MySqlTable insertEmployeeTable() {
        MySqlTable mySqlTable = new MySqlTable("employee");
        mySqlTable.addColumns(new MySqlColumns("opercode", CommonEnum.DATATYPE.INT, CommonEnum.NULLABLE.NO, CommonEnum.PRIMARYKEY.YES));
        mySqlTable.addColumns(new MySqlColumns("empname", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("username", CommonEnum.DATATYPE.VARCHAR, 20, CommonEnum.NULLABLE.YES, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("email", CommonEnum.DATATYPE.VARCHAR, 50, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, null));
        mySqlTable.addColumns(new MySqlColumns("active", CommonEnum.DATATYPE.VARCHAR, 1, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, "Y"));
        mySqlTable.addColumns(new MySqlColumns("createddate", CommonEnum.DATATYPE.DATE, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO, "YES"));

        return mySqlTable;
    }

}
