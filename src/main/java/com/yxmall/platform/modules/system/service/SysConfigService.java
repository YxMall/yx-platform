package com.yxmall.platform.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.modules.system.entity.SysConfig;
import com.yxmall.platform.modules.system.entity.SysMenu;

import java.util.Map;

/**
 * @author:smile
 * @Date:2018/10/21
 * @Time:下午4:44
 */
public interface SysConfigService extends IService<SysConfig> {


    /**
     * 根据key获取当前使用系统配置
     *
     * @param key 配置key
     * @return
     */
    SysConfig getSysConfigByKey(String key);

}
