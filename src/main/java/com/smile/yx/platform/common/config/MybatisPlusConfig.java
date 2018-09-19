package com.smile.yx.platform.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: mybatis plus 配置类
 * @author: qing.wang.o
 * @create: 2018-09-18 13:22
 **/
@Configuration
@MapperScan("com.smile.yx.platform.mapper")
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}