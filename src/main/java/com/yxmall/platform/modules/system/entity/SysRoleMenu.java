package com.yxmall.platform.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * 角色与菜单对应关系(SysRoleMenu)表实体类
 *
 * @author qing.wang.o
 * @since 2018-09-27 10:14:05
 */
@Data
public class SysRoleMenu extends Model<SysRoleMenu> {

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 角色ID
     **/
    private Long roleId;
    /**
     * 菜单ID
     **/
    private Long menuId;


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