package com.aathavan.dbinstall.model;

import com.aathavan.dbinstall.common.CommonEnum;
import lombok.Getter;

public class MySqlColumns {
    @Getter
    private int length;
    @Getter
    private int scale;
    private String defaultvalue;
    @Getter
    private String columnname;
    private CommonEnum.DATATYPE columnDataType;
    private CommonEnum.NULLABLE NULLABLE = CommonEnum.NULLABLE.YES;
    private CommonEnum.UNIQUEKEY UNIQUEKEY = CommonEnum.UNIQUEKEY.NO;
    private CommonEnum.PRIMARYKEY PRIMARYKEY = CommonEnum.PRIMARYKEY.NO;


    public MySqlColumns(String columnname, CommonEnum.DATATYPE columnDataType, CommonEnum.NULLABLE NULLABLE, CommonEnum.UNIQUEKEY UNIQUEKEY, String defaultvalue) {
        this.columnname = columnname;
        this.columnDataType = columnDataType;
        this.NULLABLE = NULLABLE;
        this.UNIQUEKEY = UNIQUEKEY;
        this.defaultvalue = defaultvalue;
    }

    public MySqlColumns(String columnname, CommonEnum.DATATYPE columnDataType, CommonEnum.NULLABLE NULLABLE, CommonEnum.PRIMARYKEY primarykey) {
        this.columnname = columnname;
        this.columnDataType = columnDataType;
        this.NULLABLE = NULLABLE;
        this.PRIMARYKEY = primarykey;
    }

    public MySqlColumns(String columnname, CommonEnum.DATATYPE columnDataType, int length, CommonEnum.NULLABLE NULLABLE, CommonEnum.UNIQUEKEY UNIQUEKEY, String defaultvalue) {
        this.columnname = columnname;
        this.columnDataType = columnDataType;
        this.NULLABLE = NULLABLE;
        this.length = length;
        this.UNIQUEKEY = UNIQUEKEY;
        this.defaultvalue = defaultvalue;
    }

    public MySqlColumns(String columnname, CommonEnum.DATATYPE columnDataType, int length, int scale, CommonEnum.NULLABLE NULLABLE) {
        this.columnname = columnname;
        this.columnDataType = columnDataType;
        this.NULLABLE = NULLABLE;
        this.length = length;
        this.scale = scale;
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
            sb.append(" PRIMARY KEY ");
        if (defaultvalue != null && !defaultvalue.trim().isEmpty()) {
            if (CommonEnum.DATATYPE.DATE == columnDataType)
                sb.append(" DEFAULT (CURRENT_DATE()) ");
            else if (CommonEnum.DATATYPE.DATETIME == columnDataType)
                sb.append(" DEFAULT (CURRENT_TIMESTAMP) ");
            else
                sb.append(" DEFAULT '").append(defaultvalue).append("' ");
        }

        return sb.toString();
    }

    public String getColumnForSp() {
        StringBuilder sb = new StringBuilder();
        sb.append(columnname).append(" ").append(getDataTypes());
        if (length > 0)
            sb.append("(").append(length).append(scale > 0 ? " ," + scale + " )" : " )");

        return sb.toString();
    }

    public String getDataTypes() {
        switch (columnDataType) {
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
            case LONGBLOB -> {
                return "LONGBLOB";
            }
        }
        return "";
    }
}
