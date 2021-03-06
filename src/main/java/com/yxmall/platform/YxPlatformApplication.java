package com.yxmall.platform;

import com.yxmall.platform.common.dataSource.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author qing.wang.o
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync //开启异步调用
@ComponentScan(basePackages = {"com.yxmall.platform.*"})
@Import({DynamicDataSourceRegister.class}) // 注册动态多数据源
public class YxPlatformApplication {
    protected final static Logger logger = LoggerFactory.getLogger(YxPlatformApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(YxPlatformApplication.class, args);
    }


    // 开发时候配置跨域，现在使用vue-cli 自己的代理解决，生成部署，是用Nginx 代理
    @Bean
    @Order(Integer.MAX_VALUE)
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
