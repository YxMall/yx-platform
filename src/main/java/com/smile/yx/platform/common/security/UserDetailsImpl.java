package com.smile.yx.platform.common.security;

import com.smile.yx.platform.vo.UserVO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
    private boolean status;
//    private List<SysRole> roleList;

    public UserDetailsImpl(UserVO userVo) {
        userVo = Optional.ofNullable(userVo).orElseGet(() -> new UserVO());
        this.userId = userVo.getUserId();
        this.username = userVo.getUsername();
        this.password = userVo.getPassword();
        this.status = userVo.getEnableFlag();
//        roleList = userVo.getRoleList();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
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
