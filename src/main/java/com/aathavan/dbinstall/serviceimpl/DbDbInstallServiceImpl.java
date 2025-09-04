package com.aathavan.dbinstall.serviceimpl;

import com.aathavan.dbinstall.common.DbInstallConstant;
import com.aathavan.dbinstall.model.MySqlTable;
import com.aathavan.dbinstall.service.DbInstallService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class DbDbInstallServiceImpl implements DbInstallService {


    @Override
    public void installTable(List<MySqlTable> tableName, String dbName) throws Exception {
        List<String> installQuery = new LinkedList<>();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DbInstallConstant.getDataSource());

        for (MySqlTable mySqlTable : tableName) {
            if (checkTableAlreadyExist(mySqlTable.getTablename(), dbName, jdbcTemplate)) {

            }
        }

    }

    private boolean checkTableAlreadyExist(String tableName, String dbName, JdbcTemplate jdbcTemplate) {
        try {
            return true;
        } catch (Exception e) {
            return false;
        }


    }


}
