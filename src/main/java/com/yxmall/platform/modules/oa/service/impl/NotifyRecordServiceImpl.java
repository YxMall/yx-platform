package com.yxmall.platform.modules.oa.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.common.utils.Query;
import com.yxmall.platform.modules.oa.entity.Notify;
import com.yxmall.platform.modules.oa.entity.NotifyRecord;
import com.yxmall.platform.modules.oa.mapper.NotifyMapper;
import com.yxmall.platform.modules.oa.mapper.NotifyRecordMapper;
import com.yxmall.platform.modules.oa.service.NotifyRecordService;
import com.yxmall.platform.modules.oa.vo.NotifyRecordVo;
import com.yxmall.platform.modules.oa.vo.NotifyVo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @description:
 * @author: qing.wang.o
 * @create: 2018-12-14 11:33
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NotifyRecordServiceImpl extends ServiceImpl<NotifyRecordMapper, NotifyRecord> implements NotifyRecordService {
    @Override
    public PageUtils getNotifyRecordListPage(Map<String, Object> params) {
        IPage<NotifyRecordVo> page = baseMapper.selectNotifyRecordPage(new Query<Notify>(params).getPage(), params);
        return new PageUtils(page);
    }
}
