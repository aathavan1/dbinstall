package com.aathavan.dbinstall.daoimpl;

import com.aathavan.dbinstall.common.DbInstallConstant;
import com.aathavan.dbinstall.dao.DbInstallDao;
import com.aathavan.dbinstall.query.Querry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DbInstallDaoImpl implements DbInstallDao {
    @Autowired
    private Querry querry;

    @Override
    public boolean checkExist(String query, JdbcTemplate jdbcTemplate) throws Exception {
        try {
            jdbcTemplate.queryForObject(query, String.class);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @Override
    public void executeQuery(String query, JdbcTemplate jdbcTemplate) throws Exception {
        jdbcTemplate.execute(query);
    }
}
