package com.yxmall.platform.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户与角色对应关系(SysUserRole)表实体类
 *
 * @author qing.wang.o
 * @since 2018-09-27 10:14:05
 */
@Data
public class SysUserRole extends Model<SysUserRole> {

    @TableId
    private Long id;
    /**
     * 用户ID
     **/
    private Long userId;
    /**
     * 角色ID
     **/
    private Long roleId;


    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}