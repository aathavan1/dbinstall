package com.aathavan.dbinstall.model;

import com.aathavan.dbinstall.common.CommonEnum;

public class MySqlColumns {

    private int length;
    private String columnname;
    private CommonEnum.DataType dataType;
    private CommonEnum.Nullable nullable;

    MySqlColumns(String columnname, CommonEnum.DataType dataType, CommonEnum.Nullable nullable) {
        this.columnname = columnname;
        this.dataType = dataType;
        this.nullable = nullable;
    }

    MySqlColumns(String columnname, CommonEnum.DataType dataType, int length, CommonEnum.Nullable nullable) {
        this.columnname = columnname;
        this.dataType = dataType;
        this.nullable = nullable;
        this.length = length;
    }
}
