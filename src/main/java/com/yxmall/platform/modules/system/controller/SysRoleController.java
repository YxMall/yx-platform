package com.yxmall.platform.modules.system.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.yxmall.platform.modules.system.service.SysRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色(SysRole)表控制层
 *
 * @author qing.wang.o
 * @since 2018-09-27 10:14:05
 */
@RestController
@RequestMapping("sysRole")
public class SysRoleController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleService sysRoleService;

}