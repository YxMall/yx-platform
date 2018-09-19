package com.smile.yx.platform.common.security;

import com.smile.yx.platform.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 认证接口
 * @author: qing.wang.o
 * @create: 2018-09-13 11:49
 **/
@RestController
@Slf4j
public class AuthenticationController {

    /**
     * 登录认证处理
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/authentication/require")
    public Result requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return Result.error(HttpStatus.UNAUTHORIZED.value(), "访问的服务需要身份认证");
    }

}
