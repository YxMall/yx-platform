package com.yxmall.platform.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.modules.system.entity.SysRole;

import java.util.List;
import java.util.Map;

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

    /**
     * 分页查询角色
     *
     * @param params
     * @return
     */
    PageUtils getRoleListPage(Map<String, Object> params);

    /**
     *删除角色
     *
     * @param id
     * @return
     */
    Result deleteRoleById(Long id);


    /**
     * 添加角色信息
     *
     * @param sysRole
     * @return
     */
    Result addRole(SysRole sysRole);

    /**
     * 修改角色信息
     *
     * @param sysRole
     * @return
     */
    Result updateRole(SysRole sysRole);

    /**
     * 获取角色信息
     *
     * @param roleId 角色id
     * @return
     */
    Result getRoleInfo(Long roleId);
}