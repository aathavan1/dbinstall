package com.aathavan.dbinstall.install;

import com.aathavan.dbinstall.common.CommonEnum;
import com.aathavan.dbinstall.model.MySqlColumns;
import com.aathavan.dbinstall.model.MySqlTable;
import org.springframework.stereotype.Component;

@Component
public class MasterTable {

    public MySqlTable insertOperatorTable() {
        MySqlTable mySqlTable = new MySqlTable();
        mySqlTable.setTableName("operator", false);
        mySqlTable.addColumns(new MySqlColumns("opercode", CommonEnum.DATATYPE.INT, CommonEnum.NULLABLE.NO, CommonEnum.unique.NO));
        mySqlTable.addColumns(new MySqlColumns("opercode", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.unique.NO));
        mySqlTable.addColumns(new MySqlColumns("active", CommonEnum.DATATYPE.VARCHAR, 1, CommonEnum.NULLABLE.NO, CommonEnum.unique.NO));
        mySqlTable.addColumns(new MySqlColumns("createddate", CommonEnum.DATATYPE.DATE, CommonEnum.NULLABLE.NO, CommonEnum.unique.NO));

        return mySqlTable;
    }

    public MySqlTable insertProductTable() {
        MySqlTable mySqlTable = new MySqlTable();
        mySqlTable.setTableName("product", false);
        mySqlTable.addColumns(new MySqlColumns("productcode", CommonEnum.DATATYPE.INT, CommonEnum.NULLABLE.NO, CommonEnum.unique.NO));
        mySqlTable.addColumns(new MySqlColumns("productname", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.unique.NO));
        mySqlTable.addColumns(new MySqlColumns("categorycode", CommonEnum.DATATYPE.INT, CommonEnum.NULLABLE.NO, CommonEnum.unique.NO));
        mySqlTable.addColumns(new MySqlColumns("description", CommonEnum.DATATYPE.VARCHAR, 30, CommonEnum.NULLABLE.NO, CommonEnum.unique.NO));
        mySqlTable.addColumns(new MySqlColumns("active", CommonEnum.DATATYPE.VARCHAR, 1, CommonEnum.NULLABLE.NO, CommonEnum.unique.NO));
        mySqlTable.addColumns(new MySqlColumns("createddate", CommonEnum.DATATYPE.DATE, CommonEnum.NULLABLE.NO, CommonEnum.unique.NO));

        return mySqlTable;
    }

}
