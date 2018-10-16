package com.yxmall.platform.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxmall.platform.modules.system.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 菜单管理(SysMenu)表数据库访问层
 *
 * @author qing.wang.o
 * @since 2018-09-27 10:14:05
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {


    /**
     * 根据角色获取菜单
     *
     * @param userId
     * @return
     */
    Set<String> selectPermsByUserId(@Param("userId") Long userId);

    /**
     * 获取用户所有权限ID
     *
     * @param userId
     * @return
     */
    List<Long> selectAllMenuId(@Param("userId") Long userId);
}