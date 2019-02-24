package com.yxmall.platform.modules.oa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.modules.oa.entity.Notify;
import com.yxmall.platform.modules.oa.form.NotifyForm;

import java.util.Map;

/**
 * 通知
 *
 * @author smalljop
 * @email 250543222@qq.com
 * @date 20181204 14:40:44
 */
public interface NotifyService extends IService<Notify> {

    /**
     * 获取当前用户发送列表
     *
     * @param params 参数
     * @return 分页对象
     */
    PageUtils getCurrentUserSendList(Map<String, Object> params);


    /**
     * 发送消息
     *
     * @param notify
     * @return
     */
    boolean sendNotify(NotifyForm notify);
}
