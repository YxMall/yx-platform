package com.yxmall.platform.security.mobile;

import com.yxmall.platform.security.UserDetailsServiceImpl;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @description: 短信登录处理
 * @author: qing.wang.o
 * @create: 2019-01-29 13:24
 **/
public class MobileAuthenticationProvider implements AuthenticationProvider {

    @Setter
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取token
        MobileAuthenticationToken token = (MobileAuthenticationToken) authentication;
        //根据手机号获取
        UserDetails user = userDetailsService.loadUserByMobile((String) token.getPrincipal());
        if (null == user) {
            throw new UsernameNotFoundException("用户不存在");
        }
        //用户 授权信息
        MobileAuthenticationToken authenticationResult = new MobileAuthenticationToken(user, user.getAuthorities());
        authenticationResult.setDetails(token);
        return authenticationResult;
    }


    /**
     * AuthenticationProvider  不同的登录类型有不同的Provider 处理
     * 通过supports方法传入参数authentication  如果返回true就启用改provider
     *
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return MobileAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
