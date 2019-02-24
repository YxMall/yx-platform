package com.yxmall.platform.modules.system.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.fasterxml.jackson.annotation.JsonView;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.common.validator.ValidatorUtils;
import com.yxmall.platform.common.validator.group.AddGroup;
import com.yxmall.platform.common.validator.group.UpdateGroup;

import com.yxmall.platform.modules.system.entity.SysDictData;
import com.yxmall.platform.modules.system.service.SysDictDataService;
import com.yxmall.platform.modules.system.vo.DictVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 数据字典索引表
 *
 * @author smalljop
 * @email 250543222@qq.com
 * @date 20181229 10:21:55
 */
@RestController
@RequestMapping("/sys/dictdata")
public class SysDictDataController {
    @Autowired
    private SysDictDataService dictDataService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @ApiOperation(value = "获取数据字典索引表", notes = "获取数据字典索引表分页信息")
    public PageUtils getDictDataListPage(@RequestParam Map<String, Object> params) {
        return dictDataService.getDictDataListPage(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/get/{dataId:\\d+}")
    @ApiOperation(value = "数据字典索引表信息", notes = "根据ID获取数据字典索引表信息")
    public Result info(@PathVariable("dataId") Integer dataId) {
        return Result.success(dictDataService.getById(dataId));
    }

    /**
     * 保存
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加数据字典索引表", notes = "添加数据字典索引表信息")
    public Result addDictData(@RequestBody SysDictData dictData) {
        dictData.setCreateTime(new Date());
        return Result.isSuccess(dictDataService.save(dictData));
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @ApiOperation(value = "修改数据字典索引表", notes = "修改数据字典索引表信息")
    public Result updateDictData(@RequestBody SysDictData dictData) {
        return Result.isSuccess(dictDataService.updateById(dictData));
    }


    /**
     * 删除
     */
    @DeleteMapping("/delete/{dataId:\\d+}")
    @ApiOperation(value = "删除数据字典索引表", notes = "根据ID删除数据字典索引表")
    public Result deleteDictData(@PathVariable("dataId") Integer dataId) {
        return Result.isSuccess(dictDataService.removeById(dataId));
    }


    /**
     * 删除
     */
    @GetMapping("/getAllDict")
    @ApiOperation(value = "获取全部字典数据", notes = "获取全部字典数据")
    public List<DictVO> getAllDict() {
        return dictDataService.getAllDict();
    }

}
