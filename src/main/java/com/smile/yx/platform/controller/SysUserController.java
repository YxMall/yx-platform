package com.smile.yx.platform.controller;


import com.smile.yx.platform.common.utils.PageUtils;
import com.smile.yx.platform.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Map;

/**
 * 系统用户(SysUser)表控制层
 *
 * @author qing.wang.o
 * @since 2018-09-12 17:13:32
 */
@RestController
@RequestMapping("sysUser")
@Api(value = "系统用户接口", description = "包含系统用户创建，新增，修改，删除等接口")
public class SysUserController  {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;

    /**
     * 分页查询所有数据
     *
     * @param params 查询条件
     * @return 所有数据
     */
    @GetMapping
    @ApiOperation(value = "查询", notes = "根据User对象创建用户")
    public PageUtils selectAll(@RequestParam Map<String, Object> params) {
        return sysUserService.queryPage(params);
    }





}