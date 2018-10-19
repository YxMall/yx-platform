package com.yxmall.platform.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxmall.platform.common.exception.BaseException;
import com.yxmall.platform.modules.system.entity.SysRoleMenu;
import com.yxmall.platform.modules.system.mapper.SysRoleMenuMapper;
import com.yxmall.platform.modules.system.service.SysRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色与菜单对应关系(SysRoleMenu)表服务实现类
 *
 * @author qing.wang.o
 * @since 2018-10-15 15:23:07
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    @Override
    public List<SysRoleMenu> getRoleMenuListByMenuId(Long menuId) {
        return baseMapper.selectList(new QueryWrapper<SysRoleMenu>().lambda().eq(SysRoleMenu::getMenuId, menuId));
    }

    @Override
    public List<SysRoleMenu> getRoleMenuListByRoleId(Long roleId) {
        return baseMapper.selectList(new QueryWrapper<SysRoleMenu>().lambda().eq(SysRoleMenu::getRoleId, roleId));
    }

    @Override
    @Transactional(rollbackFor = BaseException.class)
    public void addOrUpdate(Long roleId, List<Long> menuIds) {
        //删除角色和菜单的对应关系
        baseMapper.delete(new QueryWrapper<SysRoleMenu>().lambda().eq(SysRoleMenu::getRoleId, roleId));
        //添加角色菜单对应关系
        if (CollectionUtils.isEmpty(menuIds)) {
            return;
        }
        List<SysRoleMenu> roleMenus = menuIds.stream().map(menuId -> {
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(roleId);
            return roleMenu;
        }).collect(Collectors.toList());
        this.saveBatch(roleMenus);
    }
}