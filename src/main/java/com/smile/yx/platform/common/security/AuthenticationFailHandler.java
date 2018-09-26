package com.smile.yx.platform.common.security;

import com.smile.yx.platform.common.utils.ResponseUtil;
import com.smile.yx.platform.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.concurrent.TimeUnit;


/**
 * 登录失败处理类
 * @author smile
 */
@Slf4j
@Component
public class AuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {



    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        if (e instanceof  UsernameNotFoundException ){
            ResponseUtil.out(response,Result.error("用户名不存在"));
        }else if (e instanceof  BadCredentialsException){
            ResponseUtil.out(response,Result.error("用户名或密码错误"));
        }else if (e instanceof DisabledException){
            ResponseUtil.out(response,Result.error("用户已被禁用"));
        }else{
            ResponseUtil.out(response,Result.error());
        }
    }


}
