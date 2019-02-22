package com.yxmall.platform.security.exception;

import com.yxmall.platform.common.exception.BaseException;
import org.springframework.security.core.AuthenticationException;

/**
 * @description: 权限异常
 * @author: qing.wang.o
 * @create: 2018-11-04 14:46
 **/
public class PermissionException extends AuthenticationException {
    public PermissionException(String msg) {
        super(msg);
    }
}
