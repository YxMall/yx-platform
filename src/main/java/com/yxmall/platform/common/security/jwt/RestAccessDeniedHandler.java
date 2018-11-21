package com.yxmall.platform.common.security.jwt;


import com.yxmall.platform.common.emuns.ResponseStatusEnum;
import com.yxmall.platform.common.utils.ResponseUtil;
import com.yxmall.platform.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author smile
 */
@Component
@Slf4j
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        ResponseUtil.out(response, Result.result(ResponseStatusEnum.PERMISSION_DENIED));
    }

}
