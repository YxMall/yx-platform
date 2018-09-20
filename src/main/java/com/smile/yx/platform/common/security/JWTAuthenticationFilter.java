package com.smile.yx.platform.common.security;

import com.smile.yx.platform.common.constant.SecurityConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: Jwt权限认证过滤器
 * @author: qing.wang.o
 * @create: 2018-09-20 11:11
 **/
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(SecurityConstant.HEADER);
        if (StringUtils.isBlank(header)) {
            header = request.getParameter(SecurityConstant.HEADER);
        }
        //如果不携带token
        if (StringUtils.isBlank(header) || !header.startsWith(SecurityConstant.TOKEN_SPLIT)) {
            chain.doFilter(request, response);
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(request, response);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            e.toString();
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader(SecurityConstant.HEADER);
        if (StringUtils.isNotBlank(token)) {
            Claims claims = null;
            try {
                claims = Jwts.parser()
                        .setSigningKey(SecurityConstant.JWT_SIGN_KEY)
                        .parseClaimsJws(token.replace(SecurityConstant.TOKEN_SPLIT, ""))
                        .getBody();
                //获取用户名
                String username = claims.getSubject();
                //获取权限
                List<GrantedAuthority> authorities = new ArrayList<>();
                String authority = claims.get(SecurityConstant.AUTHORITIES).toString();
                if (StringUtils.isNotBlank(authority)) {
//                    List<String> list = new Gson().fromJson(authority, new TypeToken<List<String>>() {
//                    }.getType());
//                    for (String ga : list) {
//                        authorities.add(new SimpleGrantedAuthority(ga));
//                    }
                }
                if (StringUtils.isNotBlank(username)) {
                    User principal = new User(username, "", authorities);
                    return new UsernamePasswordAuthenticationToken(principal, null, authorities);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }


}
