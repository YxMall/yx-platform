package com.yxmall.platform.security.validate;

import com.yxmall.platform.common.constant.SecurityConstant;
import com.yxmall.platform.common.utils.RedisUtils;
import com.yxmall.platform.common.xss.XssHttpServletRequestWrapper;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @description: 验证过滤器
 * @author: qing.wang.o
 * @link OncePerRequestFilter spring提供的，保证在一个请求中只会被调用一次
 * @create: 2018-09-25 15:50
 **/
@Data
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    @Qualifier(value = "authenticationFailHandler")
    private AuthenticationFailureHandler failureHandler;
    /**
     * 系统中的校验码处理器
     */
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * 存放所有需要校验验证码的url
     */
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();
    /**
     * 验证请求url与配置的url是否匹配的工具类
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();


    /**
     * 初始化要拦截的url配置信息
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        urlMap.put(SecurityConstant.ACCOUNT_LOGIN_URL, ValidateCodeType.IMAGE);
        urlMap.put(SecurityConstant.MOBILE_LOGIN_URL, ValidateCodeType.MOBILE);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //包装请求
        XssHttpServletRequestWrapper requestWrapper = new XssHttpServletRequestWrapper(request);
        ValidateCodeType type = getValidateCodeType(request);
        if (type != null) {
            logger.info("校验请求(" + request.getRequestURI() + ")中的验证码,验证码类型" + type);
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type)
                        .validate(new ServletWebRequest(requestWrapper, response));
                logger.info("验证码校验通过");
            } catch (AuthenticationException exception) {
                failureHandler.onAuthenticationFailure(requestWrapper, response, exception);
                return;
            }
        }
        chain.doFilter(requestWrapper, response);
    }

    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     *
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        ValidateCodeType result = null;
        if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
            Set<String> urls = urlMap.keySet();
            for (String url : urls) {
                if (pathMatcher.match(url, request.getRequestURI())) {
                    result = urlMap.get(url);
                }
            }
        }
        return result;
    }


}
