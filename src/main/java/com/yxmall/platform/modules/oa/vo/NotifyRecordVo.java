package com.yxmall.platform.modules.oa.vo;

import lombok.Data;

import java.util.Date;

/**
 * @description: 通知记录视图对象
 * @author: smalljop
 * @create: 2018-12-05 09:43
 **/
@Data
public class NotifyRecordVo {


    private Long id;

    /**
     * 通知
     */
    private Long notifyId;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;

    /**
     * 发送用户
     */
    private String createUserName;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 更新时间
     */
    private Date readDate;
    /**
     * 发送时间
     */
    private Date createTime;
}
