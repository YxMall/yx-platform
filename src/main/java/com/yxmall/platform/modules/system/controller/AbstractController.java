package com.yxmall.platform.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxmall.platform.security.UserDetailsImpl;
import com.yxmall.platform.common.utils.SpringBeanUtils;
import com.yxmall.platform.modules.system.entity.SysUser;
import com.yxmall.platform.modules.system.service.SysUserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @description:
 * @author: qing.wang.o
 * @create: 2018-09-28 13:44
 **/

public abstract class AbstractController {


    /**
     * 获取当前登录用户
     *
     * @return
     */
    public UserDetails getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return userDetails;
    }


    /**
     * 获取当前登录用户ID
     *
     * @return
     */
    public Long getCurrentUserId() {
        UserDetails currentUser = getCurrentUser();
        if (currentUser != null) {
            SysUserService userService = SpringBeanUtils.getBean(SysUserService.class);
            SysUser user = userService.getOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getUsername, currentUser.getUsername()));
            return user.getUserId();
        }
        return null;
    }


}
