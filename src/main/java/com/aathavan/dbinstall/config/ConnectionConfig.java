package com.aathavan.dbinstall.config;

import com.aathavan.dbinstall.common.DbInstallConstant;
import com.aathavan.dbinstall.model.ServerCredentials;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import javax.swing.*;

public class ConnectionConfig {
    private static DataSource getDataSource(String serverIp, String portNo, String userName, String password) {
        HikariDataSource hikariDataSource = null;
        try {
            hikariDataSource = new HikariDataSource();
            hikariDataSource.setDriverClassName(DbInstallConstant.DRIVER_CLASS_NAME);
            hikariDataSource.setJdbcUrl(DbInstallConstant.prepareConnectionString(serverIp, portNo));
            hikariDataSource.setUsername(userName);
            hikariDataSource.setPassword(password);
            hikariDataSource.getConnection();
            return hikariDataSource;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public DataSource getDataSource() {
        ServerCredentials serverCredentials = DbInstallConstant.getServerCredentials();
        if (serverCredentials == null) {
            JOptionPane.showMessageDialog(null, "Server Credentials Not Found....!");
            System.exit(0);
        }

        return getDataSource(serverCredentials.getServerip(), serverCredentials.getPortno(),
                serverCredentials.getUsername(), serverCredentials.getPassword());
    }


}
