/**
 * author:smile
 * Date:2018/10/21
 * Time:下午4:45
 */
package com.yxmall.platform.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.common.utils.Query;
import com.yxmall.platform.common.utils.RedisUtils;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.modules.system.entity.SysConfig;
import com.yxmall.platform.modules.system.entity.SysRoleMenu;
import com.yxmall.platform.modules.system.entity.SysUser;
import com.yxmall.platform.modules.system.mapper.SysConfigMapper;
import com.yxmall.platform.modules.system.mapper.SysRoleMenuMapper;
import com.yxmall.platform.modules.system.service.SysConfigService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author:smile
 * @Date:2018/10/21
 * @Time:下午4:45
 */
@Service("sysConfigService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {
    private final RedisUtils redisUtils;

    @Override
    public SysConfig getSysConfigByKey(String key) {
        SysConfig config = this.getOne(new QueryWrapper<SysConfig>().lambda().eq(SysConfig::getConfigKey, key).eq(SysConfig::getStatus, true));
        return config;
    }


}
