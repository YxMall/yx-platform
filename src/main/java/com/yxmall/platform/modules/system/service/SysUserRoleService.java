package com.yxmall.platform.modules.system.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yxmall.platform.modules.system.entity.SysUserRole;

import java.util.List;

/**
 * 用户与角色对应关系(SysUserRole)表服务接口
 *
 * @author qing.wang.o
 * @since 2018-10-15 15:25:18
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 根据角色ID 查询用户角色关系集合
     *
     * @param roleId
     * @return
     */
    List<SysUserRole> getUserRoleListByRoleId(Long roleId);


    /**
     * 更新或者添加用户角色信息
     * @param userId
     * @param roleIds
     */
    void addOrUpdateUserRole(Long userId, List<Long> roleIds);
}