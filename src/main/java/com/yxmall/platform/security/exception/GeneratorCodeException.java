package com.yxmall.platform.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @description: 验证码生成异常
 * @link AuthenticationException
 * @author: qing.wang.o
 * @create: 2018-09-26 14:15
 **/
public class GeneratorCodeException extends AuthenticationException {
    public GeneratorCodeException(String msg) {
        super(msg);
    }
}
