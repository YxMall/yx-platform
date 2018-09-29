package com.yxmall.platform.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxmall.platform.modules.system.mapper.SysRoleMapper;
import com.yxmall.platform.modules.system.entity.SysRole;
import com.yxmall.platform.modules.system.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色(SysRole)表服务实现类
 *
 * @author qing.wang.o
 * @since 2018-09-27 10:14:05
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {


    @Override
    public List<SysRole> getRoleByUserId(Long userId) {
        return baseMapper.selectRoleByUserId(userId);
    }
}