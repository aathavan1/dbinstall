package com.aathavan.dbinstall.serviceimpl;

import com.aathavan.dbinstall.common.CommonEnum;
import com.aathavan.dbinstall.model.MySqlColumns;
import com.aathavan.dbinstall.model.MySqlTable;
import com.aathavan.dbinstall.service.DbInstallService;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class DbDbInstallServiceImpl implements DbInstallService {


    @Override
    public void installTable(List<MySqlTable> masterTables) throws Exception {
        List<String> installQuery = new LinkedList<>();
        for (MySqlTable mySqlTable : masterTables) {
            installQuery.add(prepareTableCreationQuery(mySqlTable));
        }

    }


    private String prepareTableCreationQuery(MySqlTable mySqlTable) {
        StringBuilder sb = new StringBuilder();
        sb.append("create table ").append(mySqlTable.getTablename()).append(" (");
        for (MySqlColumns mySqlColumns : mySqlTable.getLstColumns()) {
            sb.append(mySqlColumns.getColumnname()).append(" ");
            sb.append(mySqlColumns.getDataType().getDataTypeValue());
            if (mySqlColumns.getDataType() == CommonEnum.DataType.VARCHAR) {
                sb.append(" (").append(mySqlColumns.getLength()).append(") ");
                if (mySqlColumns.getNullable() == CommonEnum.Nullable.NO)
                    sb.append(" not null ");
                if (mySqlColumns.getUniquekey() == CommonEnum.Uniquekey.YES)
                    sb.append(" uniquekey ");
            }
            if (mySqlTable.getLstColumns().indexOf(mySqlColumns) != mySqlTable.getLstColumns().size() - 1)
                sb.append(", ");
        }
        sb.append(" )");

        return sb.toString();
    }
}
