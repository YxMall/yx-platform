package com.smile.yx.platform.common.config;

import com.smile.yx.platform.common.security.JsonAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

/**
 * @description: SpringSecurity配置类
 * @author: qing.wang.o
 * @create: 2018-09-13 11:00
 **/
@Configuration
@EnableWebSecurity
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

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                //允许所有用户访问
//                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
//                .antMatchers("/authentication/require", "/authentication/form").permitAll()
//                //其他地址的访问均需验证权限
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                //指定登录页是"/login"
//                .loginPage("/authentication/require")
//                //登录提交的地址
//                .loginProcessingUrl("/authentication/form")
//                //登录成功的处理
//                .successHandler(smileAuthenticationSuccessHandler)
//                .failureHandler(smileAuthenticationFailureHandler)
//                .permitAll()
//                .and()
//                .logout()
//                //退出登录后的默认url是"/home"
//                .logoutSuccessUrl("/home")
//                .permitAll()
//                .and().csrf().disable();
//
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry
                = http.authorizeRequests();
        registry.requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
        http.formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(smileAuthenticationSuccessHandler)
                .failureHandler(smileAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require","/code/image.jpg", "/authentication/form").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .cors()
                .and()
                .csrf().disable();
        http.addFilterAt(jsonAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);
    }


    //注册自定义的UsernamePasswordAuthenticationFilter
    @Bean
    JsonAuthenticationFilter jsonAuthenticationFilter() throws Exception {
        JsonAuthenticationFilter filter = new JsonAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(smileAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(smileAuthenticationFailureHandler);
        filter.setFilterProcessesUrl("/authentication/form");
        //这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

}
