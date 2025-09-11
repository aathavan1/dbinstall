package com.aathavan.dbinstall.model;

import java.util.HashMap;
import java.util.Map;

public class DefaultValues {
    String tablename = null;
    Map<String,Object> lsMap=new HashMap<>();


    public DefaultValues(String tablename) {
        this.tablename = tablename;
    }


}
