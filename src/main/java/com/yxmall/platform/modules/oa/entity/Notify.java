package com.yxmall.platform.modules.oa.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 通知
 *
 * @author smalljop
 * @email 250543222@qq.com
 * @date 20181204 14:40:44
 */
@Data
@TableName("oa_notify")
public class Notify implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @TableId
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
     * 更新时间
     */
    private Date updateTime;
    /**
     * 创建时间
     */
    private Date createTime;

}