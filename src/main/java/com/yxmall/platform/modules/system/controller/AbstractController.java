package com.yxmall.platform.modules.system.controller;

import com.yxmall.platform.common.security.UserDetailsImpl;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.common.utils.SpringBeanUtils;
import com.yxmall.platform.modules.system.service.SysUserService;
import com.yxmall.platform.modules.system.vo.UserVO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
            UserVO user = userService.getUserByUserName(currentUser.getUsername());
            return user.getUserId();
        }
        return null;
    }


}
