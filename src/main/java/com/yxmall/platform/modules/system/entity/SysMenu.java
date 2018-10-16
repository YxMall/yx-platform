package com.yxmall.platform.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yxmall.platform.common.validator.group.AddGroup;
import com.yxmall.platform.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 菜单管理(SysMenu)表实体类
 *
 * @author qing.wang.o
 * @since 2018-09-27 10:14:05
 */
@Data
public class SysMenu extends Model<SysMenu> {


    @TableId
    private Long menuId;
    /**
     * 父菜单ID，一级菜单为0
     **/
    @NotNull(message = "父级ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Long parentId;

    /**
     * 对应前端组件的名称
     */
    private String name;

    /**
     * 菜单名称
     **/
    @NotBlank(message = "菜单标题不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String title;
    /**
     * 菜单URL
     **/
    private String path;
    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     **/
    private String perms;
    /**
     * 类型   0：目录   1：菜单   2：按钮
     **/
    @NotNull(message = "菜单类型不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer type;
    /**
     * 菜单图标
     **/
    private String icon;
    /**
     * 排序
     **/
    private Integer orderNum;


    @TableField(exist = false)
    //为空时不惨与序列化
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<SysMenu> children;


    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }


}