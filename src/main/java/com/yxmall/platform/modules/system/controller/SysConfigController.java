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
@RequestMapping("/sys/config")
@Api(value = "系统配置接口", description = "包含系统配制一系列操作")
public class SysConfigController {

    @Autowired
    private SysConfigService sysConfigService;

    @PostMapping("/getConfig")
    @ApiOperation(value = "查询配置信息", notes = "根据key查询对应的配置信息")
    public Result getConfigList(@RequestBody String key) {
        return Result.success(sysConfigService.getSysConfigByKey(key));
    }

    @PostMapping("/saveOrUpdate")
    @ApiOperation(value = "保存或者修改系统配置", notes = "保存或者修改系统配置并且使用")
    public Result saveOrUpdate(@RequestBody SysConfig sysConfig) {
        ValidatorUtils.validateEntity(sysConfig, AddGroup.class);
        return Result.isSuccess(sysConfigService.saveOrUpdate(sysConfig));
    }

}
