package com.yxmall.platform.modules.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.yxmall.platform.common.utils.JsonUtils;
import com.yxmall.platform.modules.oa.entity.NotifyRecord;
import com.yxmall.platform.modules.oa.form.NotifyForm;
import com.yxmall.platform.modules.oa.service.NotifyRecordService;
import com.yxmall.platform.modules.oa.vo.NotifyVo;
import com.yxmall.platform.modules.system.entity.SysUser;
import com.yxmall.platform.modules.system.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.common.utils.Query;
import com.yxmall.platform.common.utils.Result;

import com.yxmall.platform.modules.oa.mapper.NotifyMapper;
import com.yxmall.platform.modules.oa.entity.Notify;
import com.yxmall.platform.modules.oa.service.NotifyService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


/**
 * @author smalljop
 */
@Service("notifyService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NotifyServiceImpl extends ServiceImpl<NotifyMapper, Notify> implements NotifyService {

    private final SysUserService sysUserService;
    private final NotifyRecordService notifyRecordService;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public PageUtils getCurrentUserSendList(Map<String, Object> params) {
        IPage<NotifyVo> page = baseMapper.selectCurrentSendList(new Query<Notify>(params).getPage(), params);
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean sendNotify(NotifyForm notify) {
        Notify dbNotify = new Notify();
        //复制属性
        BeanUtils.copyProperties(notify, dbNotify);
        dbNotify.setCreateTime(new Date());
        this.save(dbNotify);
        //添加通知记录
        List<Long> notifyUserIds = notify.getNotifyUserIds();
        //如果不存在通知的用户Id 通知全部用户
        if (CollectionUtils.isEmpty(notifyUserIds)) {
            notifyUserIds = sysUserService.list(null).stream().map(SysUser::getUserId).collect(Collectors.toList());
        }
        //保存
        Long notifyId = dbNotify.getId();
        List<NotifyRecord> notifyRecords = notifyUserIds.stream().map(userId -> {
            NotifyRecord record = new NotifyRecord();
            record.setNotifyId(notifyId);
            record.setUserId(userId);
            return record;
        }).collect(Collectors.toList());
        notifyRecordService.saveBatch(notifyRecords);
        if (CollectionUtils.isEmpty(notify.getNotifyUserIds())) {
            //发送消息
            messagingTemplate.convertAndSend("/topic/notifications", JsonUtils.objToJson(dbNotify));
        } else {
            //发送信息给指定用户
            notifyUserIds.stream().forEach(userId -> {
                messagingTemplate.convertAndSendToUser(sysUserService.getById(userId).getUsername(), "/queue/notifications", JsonUtils.objToJson(dbNotify));
            });
        }
        return true;
    }


}
