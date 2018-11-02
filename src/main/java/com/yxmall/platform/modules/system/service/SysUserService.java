package com.yxmall.platform.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.modules.system.entity.SysUser;
import com.yxmall.platform.modules.system.vo.UserVO;

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
    PageUtils getUserListPage(Map<String, Object> params);

    /**
     * 获取用户
     *
     * @param username 用户名
     * @return
     */
    UserVO getUserByUserName(String username);


    /**
     * 更改启用状态
     *
     * @param sysUser 用户id 状态
     */
    void isEnable(SysUser sysUser);

    /**
     * 添加用户信息
     *
     * @param sysUser
     * @return
     */
    Result addUser(SysUser sysUser);

    /**
     * 修改用户信息
     *
     * @param sysUser
     * @return
     */
    Result updateUser(SysUser sysUser);

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    Result getUserInfo(Long userId);

    /**
     * 检查用户名是否存在
     * @param sysUser
     * @return
     */
    Boolean checkUserName(SysUser sysUser);


    /**
     * 删除用户
     * @param userId
     * @return
     */
    Result deleteUserById(Long userId);

    /**
     * 修改当前用户信息
     * @param sysUser
     * @return
     */
    Result updateCurrentUserInfo(SysUser sysUser);
}
