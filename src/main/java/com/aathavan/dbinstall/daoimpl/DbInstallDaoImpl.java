package com.aathavan.dbinstall.daoimpl;

import com.aathavan.dbinstall.common.DbInstallConstant;
import com.aathavan.dbinstall.dao.DbInstallDao;
import com.aathavan.dbinstall.form.FormMain;
import com.aathavan.dbinstall.query.Querry;
import com.aathavan.dbinstall.serviceimpl.DbInstallServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DbInstallDaoImpl implements DbInstallDao {
    @Autowired
    private Querry querry;

    private final Logger logger = Logger.getLogger(DbInstallDaoImpl.class);

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
        try {
            jdbcTemplate.execute(query);
        } catch (Exception e) {
            logger.error(e.getMessage());
            FormMain.setTextArea(e.getMessage());
        }
    }
}
