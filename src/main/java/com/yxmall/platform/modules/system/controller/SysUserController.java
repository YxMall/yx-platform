package com.yxmall.platform.modules.system.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.common.validator.ValidatorUtils;
import com.yxmall.platform.common.validator.group.AddGroup;
import com.yxmall.platform.modules.system.entity.SysMenu;
import com.yxmall.platform.modules.system.entity.SysUser;
import com.yxmall.platform.modules.system.service.SysMenuService;
import com.yxmall.platform.modules.system.service.SysUserService;
import com.yxmall.platform.modules.system.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @GetMapping("/list")
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

    @GetMapping("/getCurrentUser")
    @ApiOperation(value = "当前用户信息", notes = "获取当前用户信息")
    public UserVO getCurrentUserInfo() {
        return sysUserService.getUserByUserName(getCurrentUser().getUsername());
    }

    @PutMapping("/updateCurrentUser")
    @ApiOperation(value = "修改用户信息", notes = "修改当前用户信息")
    public Result updateCurrentUserInfo(@RequestBody SysUser sysUser) {
        //防止用户利用该接口修改别人信息
        sysUser.setUserId(getCurrentUserId());
        return  sysUserService.updateCurrentUserInfo(sysUser);
    }

    @GetMapping("/get/{id:\\d+}")
    @ApiOperation(value = "用户基本信息", notes = "获取用户基本信息")
    public Result userInfo(@PathVariable(name = "id") Long userId) {
        return sysUserService.getUserInfo(userId);
    }


    @PostMapping("/add")
    @ApiOperation(value = "添加用户", notes = "添加用户基本信息")
    public Result addUser(@RequestBody SysUser sysUser) {
        ValidatorUtils.validateEntity(sysUser, AddGroup.class);
        sysUser.setCreateUserId(getCurrentUserId());
        return sysUserService.addUser(sysUser);
    }

    @PutMapping("/edit")
    @ApiOperation(value = "修改用户", notes = "修改用户基本信息")
    public Result updateUser(@RequestBody SysUser sysUser) {
        return sysUserService.updateUser(sysUser);
    }


    @DeleteMapping("/delete/{id:\\d+}")
    @ApiOperation(value = "删除用户", notes = "根据ID删除用户")
    public Result deleteUser(@PathVariable(name = "id") Long userId) {
        return sysUserService.deleteUserById(userId);
    }


    @PostMapping("checkName")
    @ApiOperation(value = "检查用户名是否存在", notes = "检查用户名是否存在")
    public boolean checkName(@RequestBody SysUser sysUser) {
        return sysUserService.checkUserName(sysUser);
    }

}