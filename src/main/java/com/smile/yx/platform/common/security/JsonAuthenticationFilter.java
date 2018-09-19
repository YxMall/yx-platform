package com.smile.yx.platform.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 自定义认证过滤器 支持json
 * @link UsernamePasswordAuthenticationFilter:实现Filter接口，
 * 负责拦截登录处理的url，帐号和密码会在这里获取，然后封装成Authentication交给AuthenticationManager进行认证工作
 * @author: qing.wang.o
 * @create: 2018-09-14 16:49
 **/
public class JsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //如果请求类型为json
        String contentType = request.getContentType();
        if (StringUtils.startsWithIgnoreCase(contentType, MediaType.APPLICATION_JSON_VALUE)) {
            ObjectMapper mapper = new ObjectMapper();
            UsernamePasswordAuthenticationToken authRequest = null;
            try (InputStream is = request.getInputStream()) {
                AuthenticationBean authenticationBean = mapper.readValue(is, AuthenticationBean.class);
                authRequest = new UsernamePasswordAuthenticationToken(
                        authenticationBean.getUsername(), authenticationBean.getPassword());
            } catch (IOException e) {
                e.printStackTrace();
                authRequest = new UsernamePasswordAuthenticationToken(
                        "", "");
            } finally {
                setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            }
        }
        return super.attemptAuthentication(request, response);
    }
}
