package com.aathavan.dbinstall.model;


import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DefaultValuesModel {
    @Getter
    private String tablename;
    @Getter
    private String dbname;
    List<String> columnname = new LinkedList<>();
    private List<Map<String, Object>> lstColumnValues = new LinkedList<>();

    public DefaultValuesModel(String tablename, String dbname) {
        this.tablename = tablename;
        this.dbname = dbname;
    }

    public void setColumnName(List<String> lstColumnName) {
        this.columnname = columnname;
    }

    public void setColumnValues(List<String> columnvalues) throws Exception {
        Map<String, Object> map = new LinkedHashMap<>();
        if (columnvalues.size() != columnname.size())
            throw new Exception("Invalid Column Count For " + tablename);
        for (String column : columnname) {
            map.put(column, columnvalues.get(columnname.indexOf(column)));
        }
        lstColumnValues.add(map);
    }


}
