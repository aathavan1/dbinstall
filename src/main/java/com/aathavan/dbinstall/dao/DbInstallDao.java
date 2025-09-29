package com.aathavan.dbinstall.dao;

import com.aathavan.dbinstall.model.DefaultValuesModel;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public interface DbInstallDao {


    boolean checkExist(String query, JdbcTemplate jdbcTemplate) throws Exception;

    void executeQuery(String query, JdbcTemplate jdbcTemplate);

    public List<Map<String, Object>> checkDataForDefaultValues(DefaultValuesModel defaultValuesModel);
}
