package com.yxmall.platform.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxmall.platform.modules.system.entity.SysRole;

import java.util.List;

/**
 * 角色(SysRole)表服务接口
 *
 * @author qing.wang.o
 * @since 2018-09-27 10:14:05
 */
public interface SysRoleService extends IService<SysRole> {


    /**
     * 根据用户ID获取用户角色
     *
     * @param userId 用户ID
     * @return
     */
    List<SysRole> getRoleByUserId(Long userId);
}