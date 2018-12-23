package com.yxmall.platform.modules.oa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.modules.oa.entity.Notify;
import com.yxmall.platform.modules.oa.entity.NotifyRecord;
import com.yxmall.platform.modules.oa.form.NotifyForm;

import java.util.Map;

/**
 * 通知记录
 *
 * @author smalljop
 * @email 250543222@qq.com
 * @date 20181204 14:40:44
 */
public interface NotifyRecordService extends IService<NotifyRecord> {

    /**
     * 分页
     * @param params
     * @return
     */
    PageUtils getNotifyRecordListPage(Map<String, Object> params);
}
