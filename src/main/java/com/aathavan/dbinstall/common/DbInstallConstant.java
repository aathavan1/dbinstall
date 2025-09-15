package com.aathavan.dbinstall.common;

import com.aathavan.dbinstall.model.ServerCredentials;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.time.format.DateTimeFormatter;

public class DbInstallConstant {


    private static ApplicationContext context;
    public static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static ServerCredentials serverCredentials;
    private static DataSource dataSource;

    public static class DateFormat {
        public static final DateTimeFormatter SAVEDATEFORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        public static final DateTimeFormatter DISPLAYDATEFORMAT = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        public static final DateTimeFormatter SAVEDATETIMEFORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        public static final DateTimeFormatter DISPLAYDATETIMEFORMAT = DateTimeFormatter
                .ofPattern("yyyy-MM-dd hh:mm:ss a");
        public static final DateTimeFormatter TIMEFORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
        public static final DateTimeFormatter TimeFormatWithoutSeconds = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm a");
    }


    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void setDataSource(DataSource dataSource) {
        DbInstallConstant.dataSource = dataSource;
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
