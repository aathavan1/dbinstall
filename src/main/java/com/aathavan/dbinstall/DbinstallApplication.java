package com.aathavan.dbinstall;

import com.aathavan.dbinstall.config.ApplicationConfig;
import com.aathavan.dbinstall.common.DbInstallConstant;
import com.aathavan.dbinstall.form.FormMain;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class DbinstallApplication {

    public static void main(String[] args) {
        try{



        if ((System.getProperties().getProperty("os.name") != null
                && System.getProperties().getProperty("os.name").toLowerCase().contains("windows"))) {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }

        UIManager.put("Button.defaultButtonFollowsFocus", true);
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        FormMain formMain = context.getBean(FormMain.class);
        formMain.setVisible(true);
        DbInstallConstant.setContext(context);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
