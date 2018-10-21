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
 *
 * author:smile
 * Date:2018/10/21
 * Time:下午4:45
 *
 * */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {
    @Override
    public PageUtils getConfigListPage(Map<String, Object> params) {
        String remark = (String) params.get("remark");
        String key = (String) params.get("key");
        IPage<SysConfig> page = baseMapper.selectPage(
                new Query<SysConfig>(params).getPage(),
                new QueryWrapper<SysConfig>().lambda().
                        like(StringUtils.isNotBlank(remark), SysConfig::getRemark, remark)
                        .like(StringUtils.isNotBlank(key), SysConfig::getParamKey ,key)
        );
        return new PageUtils(page);
    }
}
