package com.aathavan.dbinstall.install;

import com.aathavan.dbinstall.common.DbInstallConstant;
import com.aathavan.dbinstall.model.MySqlTable;
import com.aathavan.dbinstall.service.DbInstallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class InstallLogic {


    @Autowired
    private MasterTable masterTable;

    @Autowired
    private DbInstallService dbInstallService;


    public void installTables() throws Exception {
        dbInstallService.installTable(getMasterTables(), DbInstallConstant.getServerCredentials().getCompanycode()+"amaster");
    }


    private List<MySqlTable> getMasterTables() {
        List<MySqlTable> lstMySqlTables = new LinkedList<>();
        lstMySqlTables.add(masterTable.insertOperatorTable());
        lstMySqlTables.add(masterTable.insertProductTable());

        return lstMySqlTables;
    }


}
