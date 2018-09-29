package com.yxmall.platform.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @description: 验证码异常
 * @link AuthenticationException
 * @author: qing.wang.o
 * @create: 2018-09-26 14:15
 **/
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
