package com.yxmall.platform.security.jwt;

import com.yxmall.platform.common.utils.ResponseUtil;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.security.exception.ValidateCodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 登录失败处理类
 *
 * @author smile
 */
@Slf4j
@Component("authenticationFailHandler")
public class AuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        if (e instanceof UsernameNotFoundException) {
            ResponseUtil.outJson(response, Result.error("用户名不存在"));
        } else if (e instanceof BadCredentialsException) {
            ResponseUtil.outJson(response, Result.error("用户名或密码错误"));
        } else if (e instanceof DisabledException) {
            ResponseUtil.outJson(response, Result.error("用户已被禁用"));
        } else if (e instanceof ValidateCodeException) {
            //验证码异常
            ResponseUtil.outJson(response, Result.error(e.getMessage()));
        } else {
            ResponseUtil.outJson(response, Result.error());
        }
    }


}
