package com.yxmall.platform.modules.system.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yxmall.platform.common.validator.group.AddGroup;
import com.yxmall.platform.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

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

    @NotEmpty(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String username;

    /**
     * 昵称
     */
    private String nickName;
    /**
     * 密码
     **/
    @NotEmpty(message = "密码不能为空", groups = {AddGroup.class})
    @Pattern(regexp = "^[a-zA-Z]\\w{5,17}$", message = "以字母开头，长度在6~18之间，只能包含字母、数字和下划线", groups = {AddGroup.class})
    private String password;
    /**
     * 头像
     **/

    private String avatar;
    /**
     * 邮箱
     **/
    @Email(message = "邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
    private String email;
    /**
     * 手机号
     **/
    @Pattern(regexp = "^(13[0-9]|14[0-9]|15[0-9]|166|17[0-9]|18[0-9]|19[8|9])\\d{8}$", message = "手机号格式不正确", groups = {AddGroup.class, UpdateGroup.class})
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


    /**
     * 菜单ID
     */
    @TableField(exist = false)
    private List<Long> roleIds;

    @Override
    protected Serializable pkVal() {
        return userId;
    }


}