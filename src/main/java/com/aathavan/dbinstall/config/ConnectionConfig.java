package com.aathavan.dbinstall.config;

import com.aathavan.dbinstall.common.DbInstallCommon;
import com.aathavan.dbinstall.common.DbInstallConstant;
import com.aathavan.dbinstall.model.ServerCredentials;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import javax.swing.*;

@Configuration
public class ConnectionConfig {
    private DataSource getDataSource(String serverIp, String portNo, String userName, String password, String dbName) throws Exception {
        HikariDataSource hikariDataSource = null;
        try {
            hikariDataSource = new HikariDataSource();
            hikariDataSource.setDriverClassName(DbInstallConstant.DRIVER_CLASS_NAME);
            hikariDataSource.setJdbcUrl(DbInstallCommon.prepareConnectionString(serverIp, portNo) + dbName);
            hikariDataSource.setUsername(userName);
            hikariDataSource.setPassword(password);
            hikariDataSource.getConnection();
            return hikariDataSource;
        } catch (Exception e) {
            throw new Exception("Invalid Server Credentials");
        }
    }

    public void checkDataSource() throws Exception {
        ServerCredentials serverCredentials = DbInstallConstant.getServerCredentials();
        if (serverCredentials == null) {
            throw new Exception("Server Credentials Not Found....!");
        }

        DbInstallConstant.setDataSource(getDataSource(serverCredentials.getServerip(), serverCredentials.getPortno(),
                serverCredentials.getUsername(), serverCredentials.getPassword(), ""));
    }

    public DataSource getDbDataSource(String dbName) throws Exception {

        ServerCredentials serverCredentials = DbInstallConstant.getServerCredentials();
        if (serverCredentials == null) {
            throw new Exception("Server Credentials Not Found....!");
        }

        return getDataSource(serverCredentials.getServerip(), serverCredentials.getPortno(),
                serverCredentials.getUsername(), serverCredentials.getPassword(), dbName);
    }


}
