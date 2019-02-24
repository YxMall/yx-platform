package com.yxmall.platform.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxmall.platform.modules.system.entity.SysDictData;
import com.yxmall.platform.modules.system.entity.SysDictIndex;
import com.yxmall.platform.modules.system.mapper.SysDictDataMapper;
import com.yxmall.platform.modules.system.service.SysDictDataService;
import com.yxmall.platform.modules.system.vo.DictVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.common.utils.Query;


@Service("dictDataService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements SysDictDataService {

    @Override
    public PageUtils getDictDataListPage(Map<String, Object> params) {
        String dictCode = params.get("dictCode").toString();
        IPage<SysDictData> page = baseMapper.selectPage(
                new Query<SysDictData>(params).getPage(),
                new QueryWrapper<SysDictData>().lambda().eq(SysDictData::getDictCode, dictCode).orderByDesc(SysDictData::getSort)
        );
        return new PageUtils(page);
    }

    @Override
    public List<DictVO> getAllDict() {
        return baseMapper.selectAllDict();
    }

}
