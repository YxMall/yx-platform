package com.yxmall.platform.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxmall.platform.modules.system.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色(SysRole)表数据库访问层
 *
 * @author qing.wang.o
 * @since 2018-09-27 10:14:05
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据用户查询角色
     *
     * @param userId
     * @return
     */
    List<SysRole> selectRoleByUserId(@Param("userId") Long userId);
}