package com.yxmall.platform.modules.system.service;

import com.yxmall.platform.common.utils.PageUtils;

import java.util.Map;

/**
 * author:smile
 * Date:2018/10/21
 * Time:下午4:44
 */
public interface SysConfigService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    PageUtils getConfigListPage(Map<String,Object> params);
}
