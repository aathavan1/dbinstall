package com.aathavan.dbinstall.install;

import com.aathavan.dbinstall.model.DefaultValuesModel;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class DefaultValues {


    public DefaultValuesModel insertOperatorValues(String dbName) {
        DefaultValuesModel defaultValuesModel = new DefaultValuesModel("operator", dbName);
        defaultValuesModel.setColumnName(new LinkedList<>(List.of("aathavan","jil")));


        return defaultValuesModel;
    }
}
