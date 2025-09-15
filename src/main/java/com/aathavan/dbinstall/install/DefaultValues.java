package com.aathavan.dbinstall.install;

import com.aathavan.dbinstall.common.DbInstallConstant;
import com.aathavan.dbinstall.common.Secutity;
import com.aathavan.dbinstall.model.DefaultValuesModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Component
public class DefaultValues {
    private String dateValue = DbInstallConstant.DateFormat.SAVEDATEFORMAT.format(LocalDate.now());


    public DefaultValuesModel insertOperatorValues(String dbName) throws Exception {
        DefaultValuesModel defaultValuesModel = new DefaultValuesModel("operator", dbName);
        defaultValuesModel.setPrimaryColName("opercode");
        defaultValuesModel.setColumnName(new LinkedList<>(List.of("opercode", "opername", "password", "active", "createddate")));
        defaultValuesModel.setColumnValues(new LinkedList<>(List.of("1", "Administrator", Secutity.encrypter("123"), "Y", dateValue)));
        return defaultValuesModel;
    }
}
