package com.yxmall.platform.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxmall.platform.common.constant.CommonConstant;
import com.yxmall.platform.common.exception.BaseException;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.common.utils.Query;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.modules.system.entity.SysRoleMenu;
import com.yxmall.platform.modules.system.entity.SysUserRole;
import com.yxmall.platform.modules.system.mapper.SysRoleMapper;
import com.yxmall.platform.modules.system.entity.SysRole;
import com.yxmall.platform.modules.system.service.SysRoleMenuService;
import com.yxmall.platform.modules.system.service.SysRoleService;
import com.yxmall.platform.modules.system.service.SysUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 角色(SysRole)表服务实现类
 *
 * @author qing.wang.o
 * @since 2018-09-27 10:14:05
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final SysUserRoleService sysUserRoleService;

    private final SysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysRole> getRoleByUserId(Long userId) {
        return baseMapper.selectRoleByUserId(userId);
    }

    @Override
    public PageUtils getRoleListPage(Map<String, Object> params) {
        String roleName = (String) params.get("roleName");
        Long userId = (Long) params.get("userId");
        IPage<SysRole> page = baseMapper.selectPage(
                new Query<SysRole>(params).getPage(),
                new QueryWrapper<SysRole>().lambda().like(SysRole::getRoleName, roleName)
                        .eq(!userId.equals(CommonConstant.SUPER_ADMIN_ID), SysRole::getCreateUserId, userId)
                        .orderBy(true, true, SysRole::getCreateTime)
        );
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = BaseException.class)
    public Result deleteRoleById(Long roleId) {
        //用户角色集合
        List<SysUserRole> userRoles = sysUserRoleService.getUserRoleListByRoleId(roleId);
        if (!CollectionUtils.isEmpty(userRoles)) {
            return Result.error("该先解除对该角色拥有的用户");
        }
        //删除角色跟菜单对应关系
        sysRoleMenuService.remove(new QueryWrapper<SysRoleMenu>().lambda().eq(SysRoleMenu::getRoleId, roleId));
        //删除角色
        int flag = baseMapper.deleteById(roleId);
        return Result.isDelSuccess(flag > 0);
    }

    @Override
    @Transactional(rollbackFor = BaseException.class)
    public Result addRole(SysRole sysRole) {
        //保存角色
        sysRole.setCreateTime(new Date());
        int flag = baseMapper.insert(sysRole);
        //添加角色和菜单关系
        sysRoleMenuService.addOrUpdate(sysRole.getRoleId(), sysRole.getMenuIds());
        return Result.isAddSuccess(true);
    }

    @Override
    @Transactional(rollbackFor = BaseException.class)
    public Result updateRole(SysRole sysRole) {
        //修改角色
        int flag = baseMapper.updateById(sysRole);
        //添加角色和菜单关系
        sysRoleMenuService.addOrUpdate(sysRole.getRoleId(), sysRole.getMenuIds());
        return Result.isAddSuccess(retBool(flag));
    }


    @Override
    public Result getRoleInfo(Long roleId) {
        Result result = new Result();
        result.put("role", baseMapper.selectById(roleId));
        List<Long> menuIds = sysRoleMenuService.getRoleMenuListByRoleId(roleId).stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
        result.put("menuIds", menuIds);
        return result;
    }
}