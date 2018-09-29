package com.yxmall.platform.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxmall.platform.modules.system.entity.SysUser;
import com.yxmall.platform.modules.system.vo.UserVO;

import java.util.List;

/**
 * 系统用户(SysUser)表数据库访问层
 *
 * @author qing.wang.o
 * @since 2018-09-12 17:13:30
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    UserVO selectUserByName(String username);



}