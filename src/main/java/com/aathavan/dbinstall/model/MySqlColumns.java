package com.aathavan.dbinstall.model;

import com.aathavan.dbinstall.common.CommonEnum;

public class MySqlColumns {

    private int length;
    private String columnname;
    private CommonEnum.DataType dataType;
    private CommonEnum.Nullable nullable;
    private CommonEnum.Uniquekey uniquekey;

    public MySqlColumns(String columnname, CommonEnum.DataType dataType, CommonEnum.Nullable nullable, CommonEnum.Uniquekey uniquekey) {
        this.columnname = columnname;
        this.dataType = dataType;
        this.nullable = nullable;
        this.uniquekey = uniquekey;
    }

    public MySqlColumns(String columnname, CommonEnum.DataType dataType, int length, CommonEnum.Nullable nullable, CommonEnum.Uniquekey uniquekey) {
        this.columnname = columnname;
        this.dataType = dataType;
        this.nullable = nullable;
        this.length = length;
        this.uniquekey = uniquekey;
    }

    public int getLength() {
        return length;
    }

    public String getColumnname() {
        return columnname;
    }

    public CommonEnum.DataType getDataType() {
        return dataType;
    }

    public CommonEnum.Nullable getNullable() {
        return nullable;
    }

    public CommonEnum.Uniquekey getUniquekey() {
        return uniquekey;
    }
}
