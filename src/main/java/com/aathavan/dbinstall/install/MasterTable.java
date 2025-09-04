package com.aathavan.dbinstall.install;

import com.aathavan.dbinstall.common.CommonEnum;
import com.aathavan.dbinstall.model.MySqlColumns;
import com.aathavan.dbinstall.model.MySqlTable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MasterTable {

    public MySqlTable insertOperatorTable() {
        MySqlTable mySqlTable = new MySqlTable();
        mySqlTable.setTableName("operator", false);
        mySqlTable.addColumns(new MySqlColumns("opercode", CommonEnum.DataType.INT, CommonEnum.Nullable.NO, CommonEnum.Uniquekey.NO));
        mySqlTable.addColumns(new MySqlColumns("opercode", CommonEnum.DataType.VARCHAR, 30, CommonEnum.Nullable.NO, CommonEnum.Uniquekey.NO));
        mySqlTable.addColumns(new MySqlColumns("active", CommonEnum.DataType.VARCHAR, 1, CommonEnum.Nullable.NO, CommonEnum.Uniquekey.NO));
        mySqlTable.addColumns(new MySqlColumns("createddate", CommonEnum.DataType.DATE, CommonEnum.Nullable.NO, CommonEnum.Uniquekey.NO));

        return mySqlTable;
    }

    public MySqlTable insertProductTable() {
        MySqlTable mySqlTable = new MySqlTable();
        mySqlTable.setTableName("product", false);
        mySqlTable.addColumns(new MySqlColumns("productcode", CommonEnum.DataType.INT, CommonEnum.Nullable.NO, CommonEnum.Uniquekey.NO));
        mySqlTable.addColumns(new MySqlColumns("productname", CommonEnum.DataType.VARCHAR, 30, CommonEnum.Nullable.NO, CommonEnum.Uniquekey.NO));
        mySqlTable.addColumns(new MySqlColumns("categorycode", CommonEnum.DataType.INT, CommonEnum.Nullable.NO, CommonEnum.Uniquekey.NO));
        mySqlTable.addColumns(new MySqlColumns("description", CommonEnum.DataType.VARCHAR, 30, CommonEnum.Nullable.NO, CommonEnum.Uniquekey.NO));
        mySqlTable.addColumns(new MySqlColumns("active", CommonEnum.DataType.VARCHAR, 1, CommonEnum.Nullable.NO, CommonEnum.Uniquekey.NO));
        mySqlTable.addColumns(new MySqlColumns("createddate", CommonEnum.DataType.DATE, CommonEnum.Nullable.NO, CommonEnum.Uniquekey.NO));

        return mySqlTable;
    }
}
