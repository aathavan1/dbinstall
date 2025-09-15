package com.aathavan.dbinstall.model;

import com.aathavan.dbinstall.common.CommonEnum;

public class MySqlColumns {

    private int length;
    private int scale;

    private String columnname;
    private CommonEnum.DATATYPE dataType;
    private CommonEnum.NULLABLE NULLABLE = CommonEnum.NULLABLE.YES;
    private CommonEnum.UNIQUEKEY UNIQUEKEY = CommonEnum.UNIQUEKEY.NO;
    private CommonEnum.PRIMARYKEY PRIMARYKEY = CommonEnum.PRIMARYKEY.NO;


    public MySqlColumns(String columnname, CommonEnum.DATATYPE dataType, CommonEnum.NULLABLE NULLABLE, CommonEnum.UNIQUEKEY UNIQUEKEY) {
        this.columnname = columnname;
        this.dataType = dataType;
        this.NULLABLE = NULLABLE;
        this.UNIQUEKEY = UNIQUEKEY;
    }

    public MySqlColumns(String columnname, CommonEnum.DATATYPE dataType, CommonEnum.NULLABLE NULLABLE, CommonEnum.PRIMARYKEY unique) {
        this.columnname = columnname;
        this.dataType = dataType;
        this.NULLABLE = NULLABLE;
        this.PRIMARYKEY = unique;
    }

    public MySqlColumns(String columnname, CommonEnum.DATATYPE dataType, int length, CommonEnum.NULLABLE NULLABLE, CommonEnum.UNIQUEKEY UNIQUEKEY) {
        this.columnname = columnname;
        this.dataType = dataType;
        this.NULLABLE = NULLABLE;
        this.length = length;
        this.UNIQUEKEY = UNIQUEKEY;
    }

    public MySqlColumns(String columnname, CommonEnum.DATATYPE dataType, int length, int scale, CommonEnum.NULLABLE NULLABLE) {
        this.columnname = columnname;
        this.dataType = dataType;
        this.NULLABLE = NULLABLE;
        this.length = length;
        this.scale = scale;
    }

    public String getColumnname() {
        return columnname;
    }


    public String getColumn() {
        StringBuilder sb = new StringBuilder();
        sb.append(columnname).append(" ").append(getDataTypes());
        if (length > 0)
            sb.append("(").append(length).append(scale > 0 ? " ," + scale + " )" : " )");
        if (NULLABLE == CommonEnum.NULLABLE.NO)
            sb.append(" NOT NULL ");
        if (UNIQUEKEY == CommonEnum.UNIQUEKEY.YES)
            sb.append(" UNIQUE ");
        if (PRIMARYKEY == CommonEnum.PRIMARYKEY.YES)
            sb.append(" PRIMARYKEY ");

        return sb.toString();
    }

    private String getDataTypes() {
        switch (dataType) {
            case INT -> {
                return "INT";
            }
            case DECIMAL -> {
                return "DECIMAL";
            }
            case DATE -> {
                return "DATE";
            }
            case VARCHAR -> {
                return "VARCHAR";
            }
            case DATETIME -> {
                return "DATETIME";
            }
        }
        return "";
    }
}
