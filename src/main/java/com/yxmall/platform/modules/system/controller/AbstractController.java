package com.yxmall.platform.modules.system.controller;

import com.yxmall.platform.common.security.UserDetailsImpl;
import com.yxmall.platform.common.utils.Result;
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


}
