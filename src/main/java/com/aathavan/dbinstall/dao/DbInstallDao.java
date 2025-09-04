package com.aathavan.dbinstall.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public interface DbInstallDao {


    boolean checkExist(String query, JdbcTemplate jdbcTemplate) throws Exception;

    void executeQuery(String query, JdbcTemplate jdbcTemplate) throws Exception;
}
