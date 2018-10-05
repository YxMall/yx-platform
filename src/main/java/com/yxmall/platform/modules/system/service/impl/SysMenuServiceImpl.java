package com.yxmall.platform.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxmall.platform.common.constant.CommonConstant;
import com.yxmall.platform.modules.system.mapper.SysMenuMapper;
import com.yxmall.platform.modules.system.entity.SysMenu;
import com.yxmall.platform.modules.system.service.SysMenuService;
import com.yxmall.platform.modules.system.service.SysUserService;
import com.yxmall.platform.modules.system.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单管理(SysMenu)表服务实现类
 *
 * @author qing.wang.o
 * @since 2018-09-27 10:14:05
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {


    @Override
    public List<String> getPermsByUserId(Long userId) {
        return baseMapper.selectPermsByUserId(userId);
    }

    @Override
    public List<SysMenu> getUserMenuList(Long currentUserId) {
        //超级管理员 拥有至高无上的权利
        if (currentUserId.equals(CommonConstant.SUPER_ADMIN_ID)) {
            return getAllMenuList(null);
        }
        //用户菜单列表
        List<Long> menuIdList = this.getAllMenuId(currentUserId);
        return getAllMenuList(menuIdList);
    }

    /**
     * 获取
     *
     * @param menuIdList
     * @return
     */
    private List<SysMenu> getAllMenuList(List<Long> menuIdList) {
        //查询根菜单的列表
        List<SysMenu> menuList = getListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);
        return menuList;
    }

    /**
     * 递归获取子菜单
     *
     * @param menuList
     * @param menuIdList
     */
    private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<Long> menuIdList) {
        List<SysMenu> subList = menuList.stream().map(menu -> {
                    if (menu.getType() == CommonConstant.MenuType.CATALOG.getValue()) {
                        menu.setChildren(getMenuTreeList(getListParentId(menu.getMenuId(), menuIdList), menuIdList));
                    }
                    return menu;
                }
        ).collect(Collectors.toList());
        return subList;
    }


    /**
     * 查询menuIdList中父级菜单为parentId
     *
     * @param parentId
     * @param menuIdList
     * @return
     */
    public List<SysMenu> getListParentId(long parentId, List<Long> menuIdList) {
        //查询出全部子菜单
        List<SysMenu> menuList = getListParentId(parentId);
        if (CollectionUtils.isEmpty(menuIdList)) {
            return menuList;
        }
        List<SysMenu> userMenuList = menuList.stream().filter(menu -> menuIdList.contains(menu.getMenuId())).collect(Collectors.toList());
        return userMenuList;
    }

    /**
     * 查询父级菜单
     *
     * @param parentId 父级ID
     * @return
     */
    public List<SysMenu> getListParentId(Long parentId) {
        return baseMapper.selectList(new QueryWrapper<SysMenu>().lambda().eq(SysMenu::getParentId, parentId).orderByAsc(SysMenu::getOrderNum));
    }

    @Override
    public List<Long> getAllMenuId(Long userId) {
        return baseMapper.selectAllMenuId(userId);
    }

}