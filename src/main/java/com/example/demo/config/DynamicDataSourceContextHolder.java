package com.example.demo.config;

/**
 * @author bofei
 * @date 2018/9/13 19:15
 */
public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setDataSourceType(String dataSourceType) {

        contextHolder.set(dataSourceType);

    }



    public static String getDataSourceType() {

        return contextHolder.get();

    }



    public static void clearDataSourceType() {

        contextHolder.remove();

    }
}
