package com.aathavan.dbinstall.logic;

import com.aathavan.dbinstall.common.DbInstallConstant;
import com.aathavan.dbinstall.form.FormMain;
import com.aathavan.dbinstall.install.DefaultValues;
import com.aathavan.dbinstall.install.MasterTable;
import com.aathavan.dbinstall.model.MySqlTable;
import com.aathavan.dbinstall.service.DbInstallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class InstallLogic {


    @Autowired
    private MasterTable masterTable;

    @Autowired
    private DbInstallService dbInstallService;
    @Autowired
    private DefaultValues defaultValues;


    public void installTables() throws Exception {
        if (DbInstallConstant.getServerCredentials().getCompanycode() == null || DbInstallConstant.getServerCredentials().getCompanycode().isEmpty()) {
            FormMain.setTextArea("Company Details Not Found...!");
            return;
        }
        String masterDBName = DbInstallConstant.getServerCredentials().getCompanycode() + "amaster";
        dbInstallService.installTable(getMasterTables(), masterDBName);
        dbInstallService.defaultValues(insertDefaultValues(masterDBName));
    }


    private List<MySqlTable> getMasterTables() {
        List<MySqlTable> lstMySqlTables = new LinkedList<>();
        lstMySqlTables.add(masterTable.insertFileMainTable());
        lstMySqlTables.add(masterTable.insertOperatorTable());
        lstMySqlTables.add(masterTable.insertProductTable());
        lstMySqlTables.add(masterTable.insertCategoryTable());

        return lstMySqlTables;
    }

    private List<Object> insertDefaultValues(String dbName) {
        List<Object> lstTableData = new ArrayList<>();
        lstTableData.add(defaultValues.insertOperatorValues(dbName));
        lstTableData.add(defaultValues.insertFileMainValues(dbName));
        return lstTableData;
    }


}
