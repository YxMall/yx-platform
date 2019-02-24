package com.yxmall.platform.modules.system.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.common.validator.ValidatorUtils;
import com.yxmall.platform.common.validator.group.AddGroup;
import com.yxmall.platform.common.validator.group.UpdateGroup;

import com.yxmall.platform.modules.system.entity.SysDictIndex;
import com.yxmall.platform.modules.system.service.SysDictIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 数据字典
 *
 * @author smalljop
 * @email 250543222@qq.com
 * @date 20181229 10:19:44
 */
@RestController
@RequestMapping("/sys/dictindex")
public class SysDictIndexController {
    @Autowired
    private SysDictIndexService dictIndexService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @ApiOperation(value = "获取数据字典索引表", notes = "获取数据字典索引表分页信息")
    public PageUtils getDictIndexListPage(@RequestParam Map<String, Object> params) {
        return dictIndexService.getDictIndexListPage(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/get/{dictId:\\d+}")
    @ApiOperation(value = "数据字典索引表信息", notes = "根据ID获取数据字典索引表信息")
    public Result info(@PathVariable("dictId") Long dictId) {
        return Result.success(dictIndexService.getById(dictId));
    }

    /**
     * 保存
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加数据字典索引表", notes = "添加数据字典索引表信息")
    public Result addDictIndex(@RequestBody SysDictIndex dictIndex) {
        ValidatorUtils.validateEntity(dictIndex, UpdateGroup.class);
        dictIndex.setCreateTime(new Date());
        return Result.isSuccess(dictIndexService.save(dictIndex));
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @ApiOperation(value = "修改数据字典索引表", notes = "修改数据字典索引表信息")
    public Result updateDictIndex(@RequestBody SysDictIndex dictIndex) {
        ValidatorUtils.validateEntity(dictIndex, UpdateGroup.class);
        dictIndex.setUpdateTime(new Date());
        return Result.isSuccess(dictIndexService.updateById(dictIndex));
    }


    /**
     * 删除
     */
    @DeleteMapping("/delete/{dictId:\\d+}")
    @ApiOperation(value = "删除数据字典索引表", notes = "根据ID删除数据字典索引表")
    public Result deleteDictIndex(@PathVariable("dictId") Long dictId) {
        return Result.isSuccess(dictIndexService.removeById(dictId));
    }




}
