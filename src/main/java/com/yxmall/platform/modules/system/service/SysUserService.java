package com.yxmall.platform.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.modules.system.entity.SysUser;
import com.yxmall.platform.modules.system.vo.UserVO;

import java.util.List;
import java.util.Map;

/**
 * 系统用户(SysUser)表服务接口
 *
 * @author qing.wang.o
 * @since 2018-09-12 17:13:31
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 获取用户
     *
     * @param username 用户名
     * @return
     */
    UserVO getUserByUserName(String username);



}
