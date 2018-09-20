package com.smile.yx.platform.common.config;

import com.smile.yx.platform.common.security.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @description: SpringSecurity配置类
 * @author: qing.wang.o
 * @create: 2018-09-13 11:00
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    @Qualifier("smileAuthenticationSuccessHandler")
    private AuthenticationSuccessHandler smileAuthenticationSuccessHandler;
    @Autowired
    @Qualifier("smileAuthenticationFailureHandler")
    private AuthenticationFailureHandler smileAuthenticationFailureHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
                .authorizeRequests();
        registry.and()
                //表单登录方式
                .formLogin()
                .loginPage("/authentication/require")
                //登录请求url
                .loginProcessingUrl("/authentication/form")
                .permitAll()
                //成功处理类
                .successHandler(smileAuthenticationSuccessHandler)
                //失败
                .failureHandler(smileAuthenticationFailureHandler)
                .and()
                //允许网页iframe
                .headers().frameOptions().disable()
                .and()
                .logout()
                .permitAll()
                .and()
                .authorizeRequests()
                //任何请求
                .anyRequest()
                //需要身份认证
                .authenticated()
                .and()
                //关闭跨站请求防护
                .csrf().disable()
                //前后端分离采用JWT 不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //自定义权限拒绝处理类
//                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
//                .and()
                //添加自定义权限过滤器
//                .addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)
                //添加JWT过滤器 除/xboot/login其它请求都需经过此过滤器
                .addFilter(new JWTAuthenticationFilter(authenticationManager()));
    }

}
