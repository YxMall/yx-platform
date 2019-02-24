package com.yxmall.platform.security.mobile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxmall.platform.common.constant.SecurityConstant;
import com.yxmall.platform.common.utils.JsonUtils;
import com.yxmall.platform.common.xss.XssHttpServletRequestWrapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @description: 短信验证登录过滤器
 * 仿照  UsernamePasswordAuthenticationFilter
 * @author: qing.wang.o
 * @create: 2019-01-28 17:09
 **/
public class MobileAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_FORM_MOBILE_KEY = "mobile";
    private String mobileParameter = SPRING_SECURITY_FORM_MOBILE_KEY;
    /**
     * 是否只支持post
     */
    private boolean postOnly = true;


    protected MobileAuthenticationFilter() {
        //拦截符合规范的接口
        super(new AntPathRequestMatcher(SecurityConstant.MOBILE_LOGIN_URL, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            ObjectMapper instance = JsonUtils.getInstance();
            JsonNode params = instance.readTree(((XssHttpServletRequestWrapper) request).getBody());
            logger.info(request.getInputStream().toString());
            String mobile = params.get(SPRING_SECURITY_FORM_MOBILE_KEY).textValue();
            if (mobile == null) {
                mobile = "";
            }
            mobile = mobile.trim();
            MobileAuthenticationToken authRequest = new MobileAuthenticationToken(mobile);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    protected void setDetails(HttpServletRequest request, MobileAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setMobileParameter(String mobileParameter) {
        Assert.hasText(mobileParameter, "Mobile parameter must not be empty or null");
        this.mobileParameter = mobileParameter;
    }

    public final String getMobileParameter() {
        return mobileParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }


}