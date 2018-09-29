package com.yxmall.platform.modules.system.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色(SysRole)表实体类
 *
 * @author qing.wang.o
 * @since 2018-09-27 10:14:05
 */
@Data
public class SysRole extends Model<SysRole> {

    @TableId
    private Long roleId;
    /**
     * 角色名称
     **/
    private String roleName;
    /**
     * 备注
     **/
    private String remark;
    /**
     * 创建者ID
     **/
    private Long createUserId;
    /**
     * 创建时间
     **/
    private Date createTime;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }
}