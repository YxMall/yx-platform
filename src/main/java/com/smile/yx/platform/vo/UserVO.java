package com.smile.yx.platform.vo;

import com.google.common.base.Objects;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private Object status;
    /**
     * 创建者ID
     **/

    private Long createUserId;
    /**
     * 创建时间
     **/

    private Date createTime;

    /**
     * 是否禁用
     *
     * @return
     */
    public boolean getEnableFlag() {
        return Objects.equal(status, 0);
    }
}
