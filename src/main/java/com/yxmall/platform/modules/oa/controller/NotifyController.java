package com.yxmall.platform.modules.oa.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.google.common.collect.Lists;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.common.validator.ValidatorUtils;
import com.yxmall.platform.common.validator.group.AddGroup;
import com.yxmall.platform.common.validator.group.UpdateGroup;

import com.yxmall.platform.modules.oa.form.NotifyForm;
import com.yxmall.platform.modules.system.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import com.yxmall.platform.modules.oa.entity.Notify;
import com.yxmall.platform.modules.oa.service.NotifyService;


/**
 * 通知
 *
 * @author smalljop
 * @email 250543222@qq.com
 * @date 20181204 14:40:44
 */
@RestController
@RequestMapping("/oa/notify")
public class NotifyController extends AbstractController {
    @Autowired
    private NotifyService notifyService;

    /**
     * 列表
     */
    @RequestMapping("/sendList")
    @ApiOperation(value = "获取发送通知", notes = "获取已发送通知分页信息")
    public PageUtils getCurrentUserSendList(@RequestParam Map<String, Object> params) {
        params.put("createUserId", getCurrentUserId());
        return notifyService.getCurrentUserSendList(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/get/{id:\\d+}")
    @ApiOperation(value = "通知信息", notes = "根据ID获取通知信息")
    public Result info(@PathVariable("id") Long id) {
        return Result.success(notifyService.getById(id));
    }

    /**
     * 保存
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加通知", notes = "添加通知信息")
    public Result addNotify(@RequestBody NotifyForm notify) {
        ValidatorUtils.validateEntity(notify, AddGroup.class);
        notify.setCreateUserId(getCurrentUserId());
        boolean iRet = notifyService.sendNotify(notify);
        return Result.isAddSuccess(iRet);
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @ApiOperation(value = "修改通知", notes = "修改通知信息")
    public Result updateNotify(@RequestBody Notify notify) {
        return Result.isEditSuccess(notifyService.updateById(notify));
    }


    /**
     * 删除
     */
    @DeleteMapping("/delete/{id:\\d+}")
    @ApiOperation(value = "删除通知", notes = "根据ID删除通知")
    public Result deleteNotify(@PathVariable("id") Long id) {
        return Result.isDelSuccess(notifyService.removeById(id));
    }

}
