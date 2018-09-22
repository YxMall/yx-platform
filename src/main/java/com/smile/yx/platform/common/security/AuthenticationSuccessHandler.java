package com.smile.yx.platform.common.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.smile.yx.platform.common.constant.SecurityConstant;
import com.smile.yx.platform.common.utils.ResponseUtil;
import com.smile.yx.platform.common.utils.Result;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 登录成功处理类
 *
 * @author Exrickx
 */
@Slf4j
@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        List<GrantedAuthority> list = (List<GrantedAuthority>) ((UserDetails) authentication.getPrincipal()).getAuthorities();
        List<String> authorities = new ArrayList<>();
        for (GrantedAuthority g : list) {
            authorities.add(g.getAuthority());
        }
        //登陆成功生成JWT
        String token = Jwts.builder()
                //主题 放入用户名
                .setSubject(username)
                //自定义属性 放入用户拥有请求权限
                .claim(SecurityConstant.AUTHORITIES, objectMapper.writeValueAsString(authorities))
                //失效时间
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 24 * 60 * 1000))
                //签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.JWT_SIGN_KEY)
                .compact();
        token = SecurityConstant.TOKEN_SPLIT + token;
        ResponseUtil.out(response, Result.success("登录成功", token));
    }
}
