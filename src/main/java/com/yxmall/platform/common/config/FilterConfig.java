package com.yxmall.platform.common.config;

import com.yxmall.platform.common.xss.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;

/**
 * @description: 过滤器配置
 * @author: qing.wang.o
 * @create: 2019-01-29 16:27
 **/
@Configuration
public class FilterConfig {


    /**
     * xss 过滤器 优先级最高
     * 包装 XssHttpServletRequestWrapper 解决request只能使用一次
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns("/*");
        registration.setName("xssFilter");
        registration.setOrder(Integer.MAX_VALUE);
        return registration;
    }
}
