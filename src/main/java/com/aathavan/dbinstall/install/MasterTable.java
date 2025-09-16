package com.aathavan.dbinstall.install;

import com.aathavan.dbinstall.common.CommonEnum;
import com.aathavan.dbinstall.model.MySqlColumns;
import com.aathavan.dbinstall.model.MySqlTable;
import org.springframework.stereotype.Component;

@Component
public class MasterTable {

    public MySqlTable insertFileMainTable() {
        MySqlTable mySqlTable = new MySqlTable();
        mySqlTable.setTableName("filemain", false);
        mySqlTable.addColumns(new MySqlColumns("companycode", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.YES));
        mySqlTable.addColumns(new MySqlColumns("masterdbname", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO));
        mySqlTable.addColumns(new MySqlColumns("trandbname", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO));
        mySqlTable.addColumns(new MySqlColumns("financialyear", CommonEnum.DATATYPE.VARCHAR, 5, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO));

        return mySqlTable;
    }

    public MySqlTable insertOperatorTable() {
        MySqlTable mySqlTable = new MySqlTable();
        mySqlTable.setTableName("operator", false);
        mySqlTable.addColumns(new MySqlColumns("opercode", CommonEnum.DATATYPE.INT, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO));
        mySqlTable.addColumns(new MySqlColumns("opername", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO));
        mySqlTable.addColumns(new MySqlColumns("password", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO));
        mySqlTable.addColumns(new MySqlColumns("active", CommonEnum.DATATYPE.VARCHAR, 1, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO));
        mySqlTable.addColumns(new MySqlColumns("createddate", CommonEnum.DATATYPE.DATE, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO));

        return mySqlTable;
    }

    public MySqlTable insertProductTable() {
        MySqlTable mySqlTable = new MySqlTable();
        mySqlTable.setTableName("product", false);
        mySqlTable.addColumns(new MySqlColumns("productcode", CommonEnum.DATATYPE.INT, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO));
        mySqlTable.addColumns(new MySqlColumns("productname", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO));
        mySqlTable.addColumns(new MySqlColumns("categorycode", CommonEnum.DATATYPE.INT, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO));
        mySqlTable.addColumns(new MySqlColumns("description", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO));
        mySqlTable.addColumns(new MySqlColumns("mrprate", CommonEnum.DATATYPE.DECIMAL, 15,3, CommonEnum.NULLABLE.NO));
        mySqlTable.addColumns(new MySqlColumns("active", CommonEnum.DATATYPE.VARCHAR, 1, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO));
        mySqlTable.addColumns(new MySqlColumns("createddate", CommonEnum.DATATYPE.DATE, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO));

        return mySqlTable;
    }

    public MySqlTable insertCategoryTable() {
        MySqlTable mySqlTable = new MySqlTable();
        mySqlTable.setTableName("category", false);
        mySqlTable.addColumns(new MySqlColumns("catcode", CommonEnum.DATATYPE.INT, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO));
        mySqlTable.addColumns(new MySqlColumns("catname", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO));
        mySqlTable.addColumns(new MySqlColumns("mrprate", CommonEnum.DATATYPE.DECIMAL, 15,3, CommonEnum.NULLABLE.NO));
        mySqlTable.addColumns(new MySqlColumns("active", CommonEnum.DATATYPE.VARCHAR, 1, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO));
        mySqlTable.addColumns(new MySqlColumns("createddate", CommonEnum.DATATYPE.DATE, CommonEnum.NULLABLE.NO, CommonEnum.UNIQUEKEY.NO));

        return mySqlTable;
    }

}
