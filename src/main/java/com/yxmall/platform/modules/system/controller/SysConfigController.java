/**
 * author:smile
 * Date:2018/10/21
 * Time:下午4:42
 */
package com.yxmall.platform.modules.system.controller;

import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.modules.system.entity.SysConfig;
import com.yxmall.platform.modules.system.service.SysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 *
 * author:smile
 * Date:2018/10/21
 * Time:下午4:42
 *
 * */
@RestController
@RequestMapping("config")
@Api(value = "系统配制接口", description = "包含系统配制一系列操作")
public class SysConfigController {

    @Autowired
    private SysConfigService sysConfigService;

    @GetMapping("list")
    @ApiOperation(value = "查询", notes = "根据User对象创建用户")
    public PageUtils getConfigListPage(@RequestParam Map<String, Object> params) {
        return sysConfigService.getConfigListPage(params);
    }
}
