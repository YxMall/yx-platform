package com.yxmall.platform.security.validate;

import com.yxmall.platform.security.exception.ValidateCodeException;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @description: 验证码处理器
 * @author: qing.wang.o
 * @create: 2019-01-30 11:12
 **/
public interface ValidateCodeProcessor {


    /**
     * 创建验证码
     *
     * @param request
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 验证验证码
     *
     * @param request
     * @throws Exception
     */
    void validate(ServletWebRequest request) throws IOException, ValidateCodeException;
}
