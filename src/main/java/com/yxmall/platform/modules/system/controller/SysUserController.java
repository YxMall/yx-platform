package com.yxmall.platform.modules.system.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.modules.system.entity.SysMenu;
import com.yxmall.platform.modules.system.entity.SysUser;
import com.yxmall.platform.modules.system.service.SysMenuService;
import com.yxmall.platform.modules.system.service.SysUserService;
import com.yxmall.platform.modules.system.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * 系统用户(SysUser)表控制层
 *
 * @author qing.wang.o
 * @since 2018-09-12 17:13:32
 */
@RestController
@RequestMapping("user")
@Api(value = "系统用户接口", description = "包含系统用户创建，新增，修改，删除等接口")
public class SysUserController extends AbstractController {
    /**
     * 服务对象
     */
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 分页查询所有数据
     *
     * @param params 查询条件
     * @return 所有数据
     */
    @GetMapping
    @ApiOperation(value = "查询", notes = "根据User对象创建用户")
    public PageUtils getUserListPage(@RequestParam Map<String, Object> params) {
        return sysUserService.getUserListPage(params);
    }


    @GetMapping("/info")
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息以及功能菜单")
    public Result getNavMenu() throws IOException {
        UserVO user = sysUserService.getUserByUserName(getCurrentUser().getUsername());
        List<SysMenu> menuList = sysMenuService.getUserMenuList(user.getUserId());
        return Result.success().put("menu", menuList).put("permission", user.getPermsList()).put("info", user);
    }

    @GetMapping("/isEnable")
    @ApiOperation(value = "状态更改", notes = "是否启用状态")
    public Result isEnable(SysUser sysUser) throws IOException {
        sysUserService.isEnable(sysUser);
        return Result.success();
    }


    @GetMapping("/{id:\\d+}")
    @ApiOperation(value = "用户基本信息", notes = "获取用户基本信息")
    public SysUser roleInfo(@PathVariable(name = "id") Long userId) {
        return sysUserService.getById(userId);
    }


}