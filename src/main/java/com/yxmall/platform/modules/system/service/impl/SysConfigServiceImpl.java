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

import java.util.Map;

/**
 * @author:smile
 * @Date:2018/10/21
 * @Time:下午4:45
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {
    private final RedisUtils redisUtils;

    @Override
    public PageUtils getConfigListPage(Map<String, Object> params) {
        String remark = (String) params.get("remark");
        String key = (String) params.get("key");
        IPage<SysConfig> page = baseMapper.selectPage(
                new Query<SysConfig>(params).getPage(),
                new QueryWrapper<SysConfig>().lambda().
                        like(StringUtils.isNotBlank(remark), SysConfig::getRemark, remark)
                        .like(StringUtils.isNotBlank(key), SysConfig::getConfigKey, key)
        );
        return new PageUtils(page);
    }

    @Override
    public SysConfig getSysConfigByKey(String key) {
        SysConfig config = null;
        Object redisConfig = redisUtils.get(key);
        if (redisConfig != null) {
            config = (SysConfig) redisConfig;
            return config;
        }
        /**
         * 如腾旭云OSS和阿里云OSS key同名，使用其中一个就禁用其他的
         */
        config = baseMapper.selectOne(new QueryWrapper<SysConfig>().lambda().eq(SysConfig::getConfigKey, key).eq(SysConfig::getStatus, 1));
        if (config != null) {
            redisUtils.set(key, config, 1800L);
        }
        return config;
    }


}
