package com.yxmall.platform.modules.system.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.modules.system.entity.SysMenu;
import com.yxmall.platform.modules.system.service.SysMenuService;
import com.yxmall.platform.modules.system.service.SysUserService;
import com.yxmall.platform.modules.system.vo.MenuVO;
import com.yxmall.platform.modules.system.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单管理(SysMenu)表控制层
 *
 * @author qing.wang.o
 * @since 2018-09-27 10:14:05
 */
@RestController
@RequestMapping("menu")
@Api(value = "系统菜单接口", description = "包含系统功能菜单获取")
public class SysMenuController extends AbstractController {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysUserService sysUserService;



}