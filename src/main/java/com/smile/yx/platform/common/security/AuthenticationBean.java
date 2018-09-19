package com.smile.yx.platform.common.security;

import lombok.Data;

/**
 * @description: 认证对象
 * @author: qing.wang.o
 * @create: 2018-09-14 17:19
 **/
@Data
public class AuthenticationBean {
    private String username;
    private String password;
}
