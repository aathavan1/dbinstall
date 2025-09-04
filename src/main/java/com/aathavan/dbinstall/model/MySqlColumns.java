package com.aathavan.dbinstall.model;

import com.aathavan.dbinstall.common.CommonEnum;

public class MySqlColumns {

    private int length;
    private int scale;

    private String columnname;
    private CommonEnum.DATATYPE dataType;
    private CommonEnum.NULLABLE nullable;
    private CommonEnum.unique unique;


    public MySqlColumns(String columnname, CommonEnum.DATATYPE dataType, CommonEnum.NULLABLE nullable, CommonEnum.unique unique) {
        this.columnname = columnname;
        this.dataType = dataType;
        this.nullable = nullable;
        this.unique = unique;
    }

    public MySqlColumns(String columnname, CommonEnum.DATATYPE dataType, int length, CommonEnum.NULLABLE nullable, CommonEnum.unique unique) {
        this.columnname = columnname;
        this.dataType = dataType;
        this.nullable = nullable;
        this.length = length;
        this.unique = unique;
    }

    public String getColumnname() {
        return columnname;
    }



    public String getColumn() {
        StringBuilder sb = new StringBuilder();
        sb.append(columnname).append(" ").append(getDataTypes());
        if (length > 0)
            sb.append("(").append(length).append(scale > 0 ? " ," + scale + " )" : " )");
        if (nullable == CommonEnum.NULLABLE.NO)
            sb.append(" not null ");
        if (unique == CommonEnum.unique.YES)
            sb.append(" unique ");

        return sb.toString();
    }

    private String getDataTypes() {
        switch (dataType) {
            case INT -> {
                return "int";
            }
            case DECIMAL -> {
                return "decimal";
            }
            case DATE -> {
                return "date";
            }
            case VARCHAR -> {
                return "varchar";
            }
            case DATETIME -> {
                return "datetime";
            }
        }
        return "";
    }
}
