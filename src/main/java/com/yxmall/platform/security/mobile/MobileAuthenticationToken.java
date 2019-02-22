package com.yxmall.platform.security.mobile;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @description: 手机登录认证Token
 * * 编写思路：直接复制参考 UsernamePasswordAuthenticationToken 的写法
 * * 分析哪些需要哪些是不需要的。包括功能
 * @author: qing.wang.o
 * @create: 2019-01-28 17:05
 **/
public class MobileAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 500L;
    /**
     * 存放认证信息 ：
     * credentials 字段去掉
     */
    private final Object principal;


    public MobileAuthenticationToken(String mobile) {
        super((Collection) null);
        this.principal = mobile;
        this.setAuthenticated(false);
    }

    public MobileAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }


    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }

}
