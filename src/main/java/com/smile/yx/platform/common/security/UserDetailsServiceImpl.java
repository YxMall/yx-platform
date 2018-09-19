package com.smile.yx.platform.common.security;

import com.smile.yx.platform.entity.SysUser;
import com.smile.yx.platform.service.SysUserService;
import com.smile.yx.platform.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
        return new UserDetailsImpl(userVO);
    }
}
