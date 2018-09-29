package com.yxmall.platform.common.security.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;

/**
 * @description: 验证码安全配置
 * @author: qing.wang.o
 * @create: 2018-09-25 15:33
 **/
//@Component
//public class ValidateCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
//
//
//    /**
//     * 验证过滤器
//     */
//    @Autowired
//    private Filter validateCodeFilter;
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        //UsernamePasswordAuthenticationFilter为最前过滤器
//        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//}
