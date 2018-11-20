package com.yxmall.platform.common.dataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * Created by Dell on 28/10/2018.
 */
public class DynamicDataSource  extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return  DynamicDataSourceContextHolder.getDataSourceType();
    }
}
