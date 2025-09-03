package com.aathavan.dbinstall.common;

public class CommonEnum {


    public enum Image {

        BACKGROUND("BackGround.png"), LOGO_ICON("dbinstall_logo.png");
        private String imagePath = "images/";
        String value = "";

        Image(String path) {
            value = path;
        }

        public String getValue() {
            return imagePath + value;
        }
    }

    public enum DataType {
        VARCHAR, INT, DATE, DATETIME, DECIMAL;
    }

    public enum Nullable {
        YES, NO;
    }

}
