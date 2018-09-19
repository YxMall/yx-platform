package com.smile.yx.platform.common.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Strings;
import com.smile.yx.platform.common.xss.SQLFilter;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 *
 * @author Mark sunlightcs@gmail.com
 * @since 2.0.0 2017-03-14
 */
public class Query<T> extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
    /**
     * mybatis-plus分页参数
     */
    private Page<T> page;
    /**
     * 当前页码
     */
    private int currPage = 1;
    /**
     * 每页条数
     */
    private int limit = 10;

    public Query(Map<String, Object> params){
        this.putAll(params);

        //分页参数
        if(params.get("currPage") != null){
            currPage = Integer.parseInt((String)params.get("currPage"));
        }
        if(params.get("pageSize") != null){
            limit = Integer.parseInt((String)params.get("pageSize"));
        }
//
//        this.put("offset", (currPage - 1) * limit);
//        this.put("page", currPage);
//        this.put("limit", limit);

        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String sidx = SQLFilter.sqlInject((String)params.get("sidx"));
        String order = SQLFilter.sqlInject((String)params.get("order"));
        this.put("sidx", sidx);
        this.put("order", order);

        //mybatis-plus分页
        this.page = new Page<>(currPage, limit);


        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
        // page.setOptimizeCountSql(false);
        // 当 total 为非 0 时(默认为 0),分页插件不会进行 count 查询
        // 要点!! 分页返回的对象与传入的对象是同一个
        //排序
//        if(StringUtils.isNotBlank(sidx) && StringUtils.isNotBlank(order)){
//            this.page.setAscs(sidx);
//            this.page.setAsc("ASC".equalsIgnoreCase(order));
//        }

    }

    public Page<T> getPage() {
        return page;
    }

    public int getCurrPage() {
        return currPage;
    }

    public int getLimit() {
        return limit;
    }
}
