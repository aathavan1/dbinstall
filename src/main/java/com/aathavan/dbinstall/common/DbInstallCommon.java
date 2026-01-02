package com.aathavan.dbinstall.common;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DbInstallCommon {

    public static int horizontalGap(JPanel jPanel, JComponent jComponent, double per) {
        return (int) (jComponent.getX() + jComponent.getWidth() + (jPanel.getWidth() * per / 100));
    }

    public static int verticalGap(JPanel jPanel, JComponent jComponent, double per) {
        return (int) (jComponent.getY() + jComponent.getHeight() + (jPanel.getHeight() * per / 100));
    }

    public static String prepareConnectionString(String serverIp, String portNo, String dbName) {
        return "jdbc:mysql://" + serverIp + ":" + portNo + "/" + dbName + "?ssl-mode=REQUIRED";
    }

    public String getCurrentDate() {
        return DbInstallConstant.DateFormat.SAVEDATEFORMAT.format(LocalDate.now());
    }

    public String getCurrentTime() {
        return DbInstallConstant.DateFormat.SAVETIMEFORMAT.format(LocalDateTime.now());
    }
}
