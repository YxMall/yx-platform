package com.yxmall.platform.modules.system.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.common.validator.ValidatorUtils;
import com.yxmall.platform.common.validator.group.AddGroup;
import com.yxmall.platform.common.validator.group.UpdateGroup;
import com.yxmall.platform.modules.system.entity.SysMenu;
import com.yxmall.platform.modules.system.entity.SysRole;
import com.yxmall.platform.modules.system.service.SysRoleService;
import com.yxmall.platform.modules.system.service.SysUserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 角色(SysRole)表控制层
 *
 * @author qing.wang.o
 * @since 2018-09-27 10:14:05
 */
@RestController
@RequestMapping("role")
@Api(value = "系统角色接口", description = "包含系统角色获取")
public class SysRoleController extends AbstractController {
    /**
     * 服务对象
     */
    @Autowired
    private SysRoleService sysRoleService;


    @GetMapping("/list")
    @ApiOperation(value = "获取角色", notes = "获取角色分页信息")
    public PageUtils getRoleListPage(@RequestParam Map<String, Object> params) {
        //TODO 根据createUserID 只能查看自己创建的角色
        return sysRoleService.getRoleListPage(params);
    }

    @GetMapping("/all")
    @ApiOperation(value = "获取全部角色", notes = "获取全部角色信息")
    public List<SysRole> getAllRole() {
        //TODO 根据createUserID 只能查看自己创建的角色
        return sysRoleService.list(null);
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation(value = "删除角色", notes = "根据ID删除角色")
    public Result deleteRole(@PathVariable(name = "id") Long id) {
        return sysRoleService.deleteRoleById(id);
    }

    @GetMapping("/{id:\\d+}")
    @ApiOperation(value = "角色信息", notes = "根据ID获取角色信息")
    public Result roleInfo(@PathVariable(name = "id") Long roleId) {
        return sysRoleService.getRoleInfo(roleId);
    }

    @PostMapping
    @ApiOperation(value = "添加角色", notes = "添加角色信息")
    public Result addRole(@RequestBody SysRole sysRole) {
        ValidatorUtils.validateEntity(sysRole, AddGroup.class);
        return sysRoleService.addRole(sysRole);
    }

    @PutMapping
    @ApiOperation(value = "修改角色", notes = "修改角色信息")
    public Result updateRole(@RequestBody SysRole sysRole) {
        ValidatorUtils.validateEntity(sysRole, UpdateGroup.class);
        return sysRoleService.addRole(sysRole);
    }


}