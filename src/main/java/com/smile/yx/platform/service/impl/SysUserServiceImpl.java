package com.smile.yx.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smile.yx.platform.common.utils.PageUtils;
import com.smile.yx.platform.common.utils.Query;
import com.smile.yx.platform.mapper.SysUserMapper;
import com.smile.yx.platform.entity.SysUser;
import com.smile.yx.platform.service.SysUserService;
import com.smile.yx.platform.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 系统用户(SysUser)表服务实现类
 *
 * @author qing.wang.o
 * @since 2018-09-12 17:13:32
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String username = (String) params.get("username");
        Long createUserId = (Long) params.get("createUserId");
        IPage<SysUser> page = baseMapper.selectPage(
                new Query<SysUser>(params).getPage(),
                new QueryWrapper<SysUser>().lambda().like(SysUser::getUsername,username));
        return new PageUtils(page);
    }

    @Override
    public UserVO getUserByUserName(String username) {
        UserVO userVO = baseMapper.selectUserByName(username);
        return userVO;
    }
}