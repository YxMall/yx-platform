package com.yxmall.platform.common.security;

import com.yxmall.platform.common.constant.CommonConstant;
import com.yxmall.platform.modules.system.vo.UserVO;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @description: 用户详情实现
 * @link UserDetails 用户详情接口 定义用户的信息状态
 * @author: qing.wang.o
 * @create: 2018-09-13 11:18
 **/
@Data
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Long userId;
    private String username;
    private String password;
    private Boolean status;
    private Set<String> permsList;

    public UserDetailsImpl(UserVO userVo) {
        if (userVo != null) {
            BeanUtils.copyProperties(userVo, this);
            this.permsList = userVo.getPermsList();
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /**
         * 设置权限
         */
        List<GrantedAuthority> authorityList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(permsList)) {
            permsList.forEach(perm -> {
                if (StringUtils.isNotBlank(perm)) {
                    authorityList.add(new SimpleGrantedAuthority(perm));
                }
            });
        }
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    /**
     * 账户是否过期
     */
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    /**
     * 是否锁定
     */
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    /**
     * 认证是否过期
     */
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    /**
     * 是否禁用
     */
    public boolean isEnabled() {
        return status;
    }
}
