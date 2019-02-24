package com.yxmall.platform.modules.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.common.validator.ValidatorUtils;
import com.yxmall.platform.common.validator.group.AddGroup;
import com.yxmall.platform.common.validator.group.UpdateGroup;

import com.yxmall.platform.modules.oa.vo.NotifyVo;
import com.yxmall.platform.modules.system.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


import com.yxmall.platform.modules.oa.entity.NotifyRecord;
import com.yxmall.platform.modules.oa.service.NotifyRecordService;


/**
 * 通知通告发送记录
 *
 * @author smalljop
 * @email 250543222@qq.com
 * @date 20181214 15:56:00
 */
@RestController
@RequestMapping("/oa/notifyRecord")
public class NotifyRecordController extends AbstractController {
    @Autowired
    private NotifyRecordService notifyRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @ApiOperation(value = "获取通知通告发送记录", notes = "获取通知通告发送记录分页信息")
    public PageUtils getNotifyRecordListPage(@RequestParam Map<String, Object> params) {
        params.put("userId", getCurrentUserId());
        return notifyRecordService.getNotifyRecordListPage(params);
    }


    @GetMapping("/getUnreadCount")
    @ApiOperation(value = "获取当前账号未读消息记录数", notes = "获取当前账号未读消息记录数")
    public Long getUnreadCount() {
        long count = notifyRecordService.count(new QueryWrapper<NotifyRecord>().lambda()
                .eq(NotifyRecord::getUserId, getCurrentUserId())
                .eq(NotifyRecord::getReadStatus, false));
        return count;
    }


    /**
     * 修改
     */
    @PutMapping("/update")
    @ApiOperation(value = "修改通知通告发送记录", notes = "修改通知通告发送记录信息")
    public Result readStatusTag(@RequestBody NotifyRecord record) {
        notifyRecordService.update(record, new QueryWrapper<NotifyRecord>().lambda().in(!record.getRecordsId().isEmpty(), NotifyRecord::getId, record.getRecordsId()));
        return Result.success();
    }


    /**
     * 信息
     */
    @GetMapping("/get/{id:\\d+}")
    @ApiOperation(value = "通知通告发送记录信息", notes = "根据ID获取通知通告发送记录信息")
    public NotifyVo info(@PathVariable("id") Long id) {
        return notifyRecordService.getNotifyDetail(id);
    }

    /**
     * 保存
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加通知通告发送记录", notes = "添加通知通告发送记录信息")
    public Result addNotifyRecord(@RequestBody NotifyRecord notifyRecord) {
        return Result.isSuccess(notifyRecordService.save(notifyRecord));
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除通知通告发送记录", notes = "根据ID删除通知通告发送记录")
    public Result deleteNotifyRecord(@RequestBody List<Long> recordsId) {
        return Result.isSuccess(notifyRecordService.removeByIds(recordsId));
    }


}
