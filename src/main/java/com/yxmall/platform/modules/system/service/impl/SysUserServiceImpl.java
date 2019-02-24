package com.yxmall.platform.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxmall.platform.common.constant.CommonConstant;
import com.yxmall.platform.common.exception.BaseException;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.common.utils.Query;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.modules.system.entity.SysMenu;
import com.yxmall.platform.modules.system.entity.SysRole;
import com.yxmall.platform.modules.system.entity.SysUserRole;
import com.yxmall.platform.modules.system.mapper.SysUserMapper;
import com.yxmall.platform.modules.system.entity.SysUser;
import com.yxmall.platform.modules.system.service.SysMenuService;
import com.yxmall.platform.modules.system.service.SysRoleService;
import com.yxmall.platform.modules.system.service.SysUserRoleService;
import com.yxmall.platform.modules.system.service.SysUserService;
import com.yxmall.platform.modules.system.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 系统用户(SysUser)表服务实现类
 *
 * @author qing.wang.o
 * @since 2018-09-12 17:13:32
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final SysMenuService sysMenuService;
    private final SysRoleService sysRoleService;
    private final SysUserRoleService sysUserRoleService;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public PageUtils getUserListPage(Map<String, Object> params) {
        String username = (String) params.get("username");
        String status = (String) params.get("status");
        IPage<SysUser> page = baseMapper.selectPage(
                new Query<SysUser>(params).getPage(),
                new QueryWrapper<SysUser>().lambda().
                        like(StringUtils.isNotBlank(username), SysUser::getUsername, username)
                        .eq(StringUtils.isNotBlank(status), SysUser::getStatus, status)
        );
        return new PageUtils(page);
    }

    @Override
    public UserVO getUserByUserName(String username) {
        UserVO user = baseMapper.selectUserByName(username);
        user = this.setUserPermsList(user);
        return user;
    }


    /**
     * 设置用户权限
     *
     * @param user
     * @return
     */
    protected UserVO setUserPermsList(UserVO user) {
        if (user != null) {
            Long userId = user.getUserId();
            Set<String> permsList;
            //系统管理员，拥有最高权限
            if (userId.equals(CommonConstant.SUPER_ADMIN_ID)) {
                List<SysMenu> menuList = sysMenuService.list(null);
                permsList = menuList.stream().filter(sysMenu -> StringUtils.isNotBlank(sysMenu.getPerms())).map(SysMenu::getPerms).collect(Collectors.toSet());
            } else {
                permsList = sysMenuService.getPermsByUserId(userId);
            }
            user.setPermsList(permsList);
        }
        return user;
    }


    @Override
    public UserVO getUserByUserMobile(String mobile) {
        SysUser sysUser = this.getOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getMobile, mobile));
        UserVO user = null;
        user = this.setUserPermsList(user);
        if (null != sysUser) {
            user = new UserVO();
            BeanUtils.copyProperties(sysUser, user);
            user = this.setUserPermsList(user);
            return user;
        }
        return user;
    }

    @Override
    public void isEnable(SysUser sysUser) {
        baseMapper.updateById(sysUser);
    }

    @Override
    @Transactional(rollbackFor = {BaseException.class})
    public Result addUser(SysUser sysUser) {
        sysUser.setCreateTime(new Date());
        //密码加密
        String encodePwd = passwordEncoder.encode(sysUser.getPassword());
        sysUser.setPassword(encodePwd);
        //添加用户
        int flag = baseMapper.insert(sysUser);
        //更新或者添加用户角色
        sysUserRoleService.addOrUpdateUserRole(sysUser.getUserId(), sysUser.getRoleIds());
        return Result.isSuccess(retBool(flag));
    }

    @Override
    @Transactional(rollbackFor = {BaseException.class})
    public Result updateUser(SysUser sysUser) {
        if (StringUtils.isBlank(sysUser.getPassword())) {
            sysUser.setPassword(null);
        }
        //添加用户
        int flag = baseMapper.updateById(sysUser);
        //更新或者添加用户角色
        sysUserRoleService.addOrUpdateUserRole(sysUser.getUserId(), sysUser.getRoleIds());
        return Result.isSuccess(retBool(flag));
    }

    @Override
    public Result getUserInfo(Long userId) {
        Result result = new Result();
        SysUser sysUser = baseMapper.selectById(userId);
        sysUser.setPassword(null);
        result.put("user", sysUser);
        //获取该用户拥有的角色ID
        List<SysRole> sysRoles = sysRoleService.getRoleByUserId(userId);
        result.put("roleIds", sysRoles.stream().map(SysRole::getRoleId).collect(Collectors.toList()));
        return result;
    }

    @Override
    public Boolean checkUserName(SysUser sysUser) {
        String username = sysUser.getUsername();
        UserVO userVO = baseMapper.selectUserByName(username);
        if (null == userVO || sysUser.getUserId().equals(userVO.getUserId())) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = BaseException.class)
    public Result deleteUserById(Long userId) {
        if (userId.equals(CommonConstant.SUPER_ADMIN_ID)) {
            return Result.error("超级管理员无法删除");
        }
        //删除用户
        int flag = baseMapper.deleteById(userId);
        //删除用户角色关联
        sysUserRoleService.remove(new QueryWrapper<SysUserRole>().lambda().eq(SysUserRole::getUserId, userId));
        return Result.isSuccess(retBool(flag));
    }

    @Override
    public Result updateCurrentUserInfo(SysUser sysUser) {
        if (StringUtils.isBlank(sysUser.getPassword())) {
            sysUser.setPassword(null);
        }
        //添加用户
        int flag = baseMapper.updateById(sysUser);
        return Result.isSuccess(retBool(flag));
    }


}