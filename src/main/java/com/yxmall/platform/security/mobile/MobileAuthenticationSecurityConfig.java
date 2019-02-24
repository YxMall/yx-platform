package com.yxmall.platform.security.mobile;

import com.yxmall.platform.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @description: 短信认证配置
 * @author: qing.wang.o
 * @create: 2019-01-29 13:30
 **/
@Configuration
public class MobileAuthenticationSecurityConfig
        extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        MobileAuthenticationFilter filter = new MobileAuthenticationFilter();
        // 图上流程，因为最先走的 短信认证的过滤器（不是验证码，只是认证）
        // 要使用管理器来获取provider，所以把管理器注册进去
        filter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);

        MobileAuthenticationProvider smsCodeAuthenticationProvider = new MobileAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);

        http
                // 注册到AuthenticationManager中去
                .authenticationProvider(smsCodeAuthenticationProvider)
                // 添加到 UsernamePasswordAuthenticationFilter 之后
                // 貌似所有的入口都是 UsernamePasswordAuthenticationFilter
                // 然后UsernamePasswordAuthenticationFilter的provider不支持这个地址的请求
                // 所以就会落在我们自己的认证过滤器上。完成接下来的认证
                .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
