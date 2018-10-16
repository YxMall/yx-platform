package com.yxmall.platform.modules.system.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
     * 昵称
     */
    private String nickName;
    /**
     * 密码
     **/
    private String password;
    /**
     * 头像
     **/

    private String avatar;
    /**
     * 邮箱
     **/

    private String email;
    /**
     * 手机号
     **/
    private String mobile;


    /**
     * 性别  true: 男 false:女
     */
    private Boolean gender;

    /**
     * 状态  true：正常   false：禁用
     **/

    private Boolean status;
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