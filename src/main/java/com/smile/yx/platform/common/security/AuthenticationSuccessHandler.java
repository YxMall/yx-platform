package com.smile.yx.platform.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smile.yx.platform.common.constant.SecurityConstant;
import com.smile.yx.platform.common.utils.Result;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @description: 登录成功处理
 * {@link SavedRequestAwareAuthenticationSuccessHandler}Security默认的成功处理器，默认处理是跳转
 * @author: qing.wang.o
 * @create: 2018-09-13 13:31
 **/
@Component("smileAuthenticationSuccessHandler")
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        //登陆成功生成JWT
        String token = Jwts.builder()
                //主题 放入用户名
                .setSubject(username)
                //自定义属性 放入用户拥有请求权限
                .claim(SecurityConstant.AUTHORITIES, "admin")
                //失效时间
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 60 * 24 * 60 * 1000))
                //签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.JWT_SIGN_KEY)
                .compact();
        token = SecurityConstant.TOKEN_SPLIT + token;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(Result.success("登录成功", token)));
    }
}
