package com.yxmall.platform.modules.oa.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @description: 通知视图对象
 * @author: smalljop
 * @create: 2018-12-05 09:43
 **/
@Data
public class NotifyVo {

    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 状态  0：删除  1：正常
     */
    private Integer status;
    /**
     * 创建用户
     */
    private Long createUserId;

    /**
     * 阅读状态
     */
    private Boolean readStatus;

    /**
     * 创建用户
     */
    private String createUserName;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 创建时间
     */
    private Date createTime;
}
