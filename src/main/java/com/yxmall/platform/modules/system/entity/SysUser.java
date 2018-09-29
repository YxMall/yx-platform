package com.yxmall.platform.modules.system.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统用户(SysUser)表实体类
 *
 * @author qing.wang.o
 * @since 2018-09-12 17:13:29
 */
@Data
public class SysUser extends Model<SysUser> {

    @TableId
    private Long userId;
    /**
     * 用户名
     **/

    private String username;
    /**
     * 密码
     **/

    private String password;
    /**
     * 盐
     **/

    private String salt;
    /**
     * 邮箱
     **/

    private String email;
    /**
     * 手机号
     **/

    private String mobile;
    /**
     * 状态  0：正常   1：禁用
     **/

    private Integer status;
    /**
     * 创建者ID
     **/

    private Long createUserId;
    /**
     * 创建时间
     **/

    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return userId;
    }


}