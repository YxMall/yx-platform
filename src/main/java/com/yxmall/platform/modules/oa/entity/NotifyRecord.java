package com.yxmall.platform.modules.oa.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @description: 通知记录
 * @author: qing.wang.o
 * @create: 2018-12-14 11:23
 **/
@Data
@TableName(value = "oa_notify_record")
public class NotifyRecord {
    /**
     * 编号
     */
    private long id;

    /**
     * 通知通告ID
     */
    private long notifyId;

    /**
     * 接受人
     */
    private long userId;

    /**
     * 阅读标记
     */
    private boolean readStatus;

    /**
     * 阅读时间
     */
    private Date readDate;

}
