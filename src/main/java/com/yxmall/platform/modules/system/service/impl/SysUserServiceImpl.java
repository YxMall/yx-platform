package com.yxmall.platform.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxmall.platform.common.constant.CommonConstant;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.common.utils.Query;
import com.yxmall.platform.modules.system.entity.SysMenu;
import com.yxmall.platform.modules.system.mapper.SysUserMapper;
import com.yxmall.platform.modules.system.entity.SysUser;
import com.yxmall.platform.modules.system.service.SysMenuService;
import com.yxmall.platform.modules.system.service.SysRoleService;
import com.yxmall.platform.modules.system.service.SysUserService;
import com.yxmall.platform.modules.system.vo.UserVO;
import javafx.scene.control.Menu;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void isEnable(SysUser sysUser) {
        baseMapper.updateById(sysUser);
    }



}