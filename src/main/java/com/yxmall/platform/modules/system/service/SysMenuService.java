package com.yxmall.platform.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.modules.system.entity.SysMenu;
import com.yxmall.platform.modules.system.vo.ElTreeVO;

import java.util.List;
import java.util.Set;

/**
 * 菜单管理(SysMenu)表服务接口
 *
 * @author qing.wang.o
 * @since 2018-09-27 10:14:05
 */
public interface SysMenuService extends IService<SysMenu> {


    /**
     * 根据用户ID获取权限
     *
     * @param userId
     * @return
     */
    Set<String> getPermsByUserId(Long userId);


    /**
     * 获取用户所有权限ID
     *
     * @param userId
     * @return
     */
    List<Long> getAllMenuId(Long userId);

    /**
     * 获取用户菜单列表
     *
     * @param currentUserId 当前用户ID
     * @return
     */
    List<SysMenu> getUserMenuList(Long currentUserId);


    /**
     * 获取菜单树列表
     *
     * @return
     */
    List<SysMenu> getMenuTreeList();

    /**
     * 根据id删除
     * @param id
     * @return
     */
    Result deleteMenuById(Long id);
}