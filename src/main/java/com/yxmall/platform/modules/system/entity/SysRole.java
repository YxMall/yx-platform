package com.yxmall.platform.modules.system.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.yxmall.platform.common.validator.group.AddGroup;
import com.yxmall.platform.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 角色(SysRole)表实体类
 *
 * @author qing.wang.o
 * @since 2018-09-27 10:14:05
 */
@Data
public class SysRole extends Model<SysRole> {

    @TableId
    @Min(value = 1, message = "角色ID不能为空", groups = {UpdateGroup.class})
    private Long roleId;
    /**
     * 角色名称
     **/
    @NotEmpty(message = "角色名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
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
     * 菜单ID
     */
    @TableField(exist = false)
    private List<Long> menuIds;

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