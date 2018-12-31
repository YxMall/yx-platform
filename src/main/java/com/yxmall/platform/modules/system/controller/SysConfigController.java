package com.yxmall.platform.modules.system.controller;

import com.yxmall.platform.common.utils.JsonUtils;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.common.validator.ValidatorUtils;
import com.yxmall.platform.common.validator.group.AddGroup;
import com.yxmall.platform.common.validator.group.UpdateGroup;
import com.yxmall.platform.modules.system.entity.SysConfig;
import com.yxmall.platform.modules.system.entity.SysUser;
import com.yxmall.platform.modules.system.service.SysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * @author:smile Date:2018/10/21
 * Time:下午4:42
 */
@RestController
@RequestMapping("config")
@Api(value = "系统配制接口", description = "包含系统配制一系列操作")
public class SysConfigController {

    @Autowired
    private SysConfigService sysConfigService;

    @GetMapping("/list")
    @ApiOperation(value = "查询", notes = "根据User对象创建用户")
    public PageUtils getConfigListPage(@RequestParam Map<String, Object> params) {
        return sysConfigService.getConfigListPage(params);
    }

    @DeleteMapping("/delete/{id:\\d+}")
    @ApiOperation(value = "删除系统配置", notes = "根据ID删除系统配置")
    public Result deleteConfig(@PathVariable(name = "id") Long configId) {
        return Result.isSuccess(sysConfigService.removeById(configId));
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加系统配置", notes = "添加系统配置")
    public Result addConfig(@RequestBody SysConfig sysConfig) {
        ValidatorUtils.validateEntity(sysConfig, AddGroup.class);
        sysConfig.setCreateTime(new Date());
        return Result.isSuccess(sysConfigService.save(sysConfig));
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改系统配置", notes = "修改系统配置")
    public Result updateConfig(@RequestBody SysConfig sysConfig) {
        ValidatorUtils.validateEntity(sysConfig, UpdateGroup.class);
        return Result.isSuccess(sysConfigService.updateById(sysConfig));
    }

    @GetMapping("/get/{id:\\d+}")
    @ApiOperation(value = "角色信息", notes = "根据ID获取角色信息")
    public SysConfig configInfo(@PathVariable(name = "id") Long configId) {
        return sysConfigService.getById(configId);
    }
}
