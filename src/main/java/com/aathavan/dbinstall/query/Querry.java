package com.aathavan.dbinstall.query;

import org.springframework.stereotype.Component;

@Component
public class Querry {
    public String checkDbExist(String dbName) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT SCHEMA_NAME \n");
        sb.append("FROM information_schema.schemata \n");
        sb.append("WHERE SCHEMA_NAME = '").append(dbName).append("'");
        return sb.toString();
    }

    public String checkDataForDefaultValues(String tableName) {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from ").append(tableName);
        return sb.toString();
    }

    public String getTablePrefix() {
        StringBuilder sb = new StringBuilder();
        sb.append("select concat(prefix,'_') from pretable where tablename =? limit 1");

        return sb.toString();
    }
}
