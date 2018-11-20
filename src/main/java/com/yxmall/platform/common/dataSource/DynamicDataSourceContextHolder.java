package com.yxmall.platform.common.dataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 28/10/2018.
 */
public class DynamicDataSourceContextHolder {
    //将数据源标识保持在线程变量中，避免多线程操作数据时互相干扰
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    public static List<String> dataSourceIds = new ArrayList<>();

    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    public static String getDataSourceType() {
        return contextHolder.get();
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }

    /**
     * 判断指定DataSrouce当前是否存在
     *
     * @param dataSourceId
     */
    //判断当前数据源是否存在
    public static boolean isContainsDataSource(String dataSourceId) {
        return dataSourceIds.contains(dataSourceId);
    }
}
