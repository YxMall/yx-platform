package com.smile.yx.platform.common.security.validate;

import com.smile.yx.platform.common.constant.SecurityConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 验证过滤器
 * @author: qing.wang.o
 * @link OncePerRequestFilter spring提供的，保证在一个请求中只会被调用一次
 * @create: 2018-09-25 15:50
 **/
//public class ValidateCodeFilter extends OncePerRequestFilter {
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//        if (StringUtils.equalsIgnoreCase(SecurityConstant.LOGIN_URL, request.getRequestURI()) && StringUtils.equalsAnyIgnoreCase(request.getMethod(), "post")
//
//        }
//    }
//}
