package com.aathavan.dbinstall.service;

import com.aathavan.dbinstall.model.MySqlTable;

import java.util.List;

public interface DbInstallService {


    boolean installPrefixTable(List<Object> lstPretable, String masterDBName);

    void installTable(List<MySqlTable> masterTables, String masterDBName, boolean isFredhDb);

    void defaultValues(List<Object> objects);

}
