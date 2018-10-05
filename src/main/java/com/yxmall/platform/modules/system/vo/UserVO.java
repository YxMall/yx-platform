package com.yxmall.platform.modules.system.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import com.yxmall.platform.modules.system.entity.SysMenu;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class UserVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;
    /**
     * 用户名
     **/

    private String username;
    /**
     * 密码
     **/
    @JsonIgnore
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
     * 状态  0：禁用   1：正常
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



    /**
     * 功能权限列表
     */
    @JsonIgnore
    private List<String> permsList;


}
