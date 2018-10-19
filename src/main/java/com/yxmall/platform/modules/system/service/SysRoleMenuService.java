package com.yxmall.platform.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxmall.platform.modules.system.entity.SysRoleMenu;

import java.util.List;

/**
 * 角色与菜单对应关系(SysRoleMenu)表服务接口
 *
 * @author qing.wang.o
 * @since 2018-10-15 15:23:07
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {


    /**
     * 查询角色菜单集合
     *
     * @param menuId 菜单ID
     * @return
     */
    List<SysRoleMenu> getRoleMenuListByMenuId(Long menuId);

    /**
     * 查询角色菜单集合
     *
     * @param roleId 菜单ID
     * @return
     */
    List<SysRoleMenu> getRoleMenuListByRoleId(Long roleId);

    /**
     * 添加或者修改菜单角色
     *
     * @param roleId  角色ID
     * @param menuIds 菜单集合
     */
    void addOrUpdate(Long roleId, List<Long> menuIds);
}