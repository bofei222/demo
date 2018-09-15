package com.example.demo.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author bofei
 * @date 2018/9/13 19:11
 */
public class DynamicDataSource  extends AbstractRoutingDataSource {
    public DynamicDataSource () {
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
