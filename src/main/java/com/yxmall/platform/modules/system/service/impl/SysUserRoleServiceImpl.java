package com.yxmall.platform.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxmall.platform.common.exception.BaseException;
import com.yxmall.platform.modules.system.entity.SysRole;
import com.yxmall.platform.modules.system.entity.SysUserRole;
import com.yxmall.platform.modules.system.mapper.SysUserRoleMapper;
import com.yxmall.platform.modules.system.service.SysUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户与角色对应关系(SysUserRole)表服务实现类
 *
 * @author qing.wang.o
 * @since 2018-10-15 15:25:18
 */
@Service("sysUserRoleService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public List<SysUserRole> getUserRoleListByRoleId(Long roleId) {
        List<SysUserRole> sysUserRoles = baseMapper.selectList(new QueryWrapper<SysUserRole>().lambda().eq(SysUserRole::getRoleId, roleId));
        return sysUserRoles;
    }

    @Override
    @Transactional(rollbackFor = {BaseException.class})
    public void addOrUpdateUserRole(Long userId, List<Long> roleIds) {
        //删除全部角色信息
        this.remove(new QueryWrapper<SysUserRole>().lambda().eq(SysUserRole::getUserId, userId));
        //添加角色信息
        List<SysUserRole> sysUserRoles = roleIds.stream().map(roleId -> {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            return userRole;
        }).collect(Collectors.toList());
        this.saveBatch(sysUserRoles);
    }
}