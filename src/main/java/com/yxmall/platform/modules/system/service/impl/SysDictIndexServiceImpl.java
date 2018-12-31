package com.yxmall.platform.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxmall.platform.modules.system.entity.SysDictIndex;
import com.yxmall.platform.modules.system.mapper.SysDictIndexMapper;
import com.yxmall.platform.modules.system.service.SysDictIndexService;
import org.springframework.stereotype.Service;

import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.common.utils.Query;

/**
 * @author qing.wang.o
 */
@Service("dictIndexService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysDictIndexServiceImpl extends ServiceImpl<SysDictIndexMapper, SysDictIndex> implements SysDictIndexService {

    @Override
    public PageUtils getDictIndexListPage(Map<String, Object> params) {
        IPage<SysDictIndex> page = baseMapper.selectPage(
                new Query<SysDictIndex>(params).getPage(),
                new QueryWrapper<SysDictIndex>().lambda().orderByDesc(SysDictIndex::getSort)
        );
        return new PageUtils(page);
    }

}
