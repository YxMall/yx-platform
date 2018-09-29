package com.yxmall.platform.common.security.validate;

import com.yxmall.platform.common.constant.SecurityConstant;
import com.yxmall.platform.common.exception.ValidateCodeException;
import com.yxmall.platform.common.utils.RedisUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
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
@Data
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            if (StringUtils.equalsIgnoreCase(SecurityConstant.LOGIN_URL, request.getRequestURI()) &&
                    StringUtils.equalsAnyIgnoreCase(request.getMethod(), "post")) {
                validateCode(request);
            }
        } catch (ValidateCodeException e) {
            failureHandler.onAuthenticationFailure(request, response, e);
        }
        chain.doFilter(request, response);
    }

    private void validateCode(HttpServletRequest request) throws ServletRequestBindingException, ValidateCodeException {
        String uuid = ServletRequestUtils.getStringParameter(request, "uuid");
        String code = ServletRequestUtils.getStringParameter(request, "code").trim();
        String redisCode = (String) redisUtils.get(uuid);
        if (StringUtils.isBlank(code)) {
            throw new ValidateCodeException("验证码不能为空");
        }
        if (StringUtils.isBlank(redisCode)) {
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equals(redisCode, code)) {
            throw new ValidateCodeException("验证码不正确");
        }
    }


}
