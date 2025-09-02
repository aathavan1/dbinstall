package com.aathavan.dbinstall.common;

import com.aathavan.dbinstall.model.ServerCredentials;
import org.springframework.context.ApplicationContext;

public class DbInstallConstant {


    private static ApplicationContext context;


    public static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    private static ServerCredentials serverCredentials;


    public static String prepareConnectionString(String serverIp, String portNo) {
        return "jdbc:mysql://" + serverIp + ":" + portNo + "/";
    }

    public static void setContext(ApplicationContext context) {
        DbInstallConstant.context = context;
    }


    public static ServerCredentials getServerCredentials() {
        return serverCredentials;
    }

    public static void setServerCredentials(ServerCredentials serverCredentials) {
        DbInstallConstant.serverCredentials = serverCredentials;
    }

    public static ApplicationContext getContext() {
        return context;
    }


}
