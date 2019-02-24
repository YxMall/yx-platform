package com.yxmall.platform.security;

import com.yxmall.platform.modules.system.service.SysUserService;
import com.yxmall.platform.modules.system.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @description: SpringSecurity 用户认证实现
 * @author: qing.wang.o
 * @create: 2018-09-13 11:06
 **/
@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登陆用户名： {}", username);
        UserVO userVO = sysUserService.getUserByUserName(username);
        if (userVO != null) {
            return new UserDetailsImpl(userVO);
        }
        return null;
    }

    public UserDetailsImpl loadUserByMobile(String mobile) throws UsernameNotFoundException {
        log.info("登录手机号： {}", mobile);
        UserVO userVO = sysUserService.getUserByUserMobile(mobile);
        if (userVO != null) {
            return new UserDetailsImpl(userVO);
        }
        return null;
    }

}
