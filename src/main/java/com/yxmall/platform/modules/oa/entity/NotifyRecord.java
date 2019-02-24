package com.yxmall.platform.modules.oa.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

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
    private Long id;

    /**
     * 通知通告ID
     */
    private Long notifyId;

    /**
     * 接受人
     */
    private Long userId;

    /**
     * 阅读标记
     */
    private Boolean readStatus;

    /**
     * 阅读时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date readDate;

    /**
     * 删除状态
     */
    private Boolean status;

    /**
     * 批量操作的ID
     */
    @TableField(exist = false)
    private List<Long> recordsId;

}
