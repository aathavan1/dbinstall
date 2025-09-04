package com.aathavan.dbinstall.service;

import com.aathavan.dbinstall.model.MySqlTable;

import java.util.List;

public interface DbInstallService {
    void installTable(List<MySqlTable> masterTables, String aathav) throws Exception;
}
