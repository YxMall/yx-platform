package com.yxmall.platform.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxmall.platform.modules.system.entity.SysFile;
import com.yxmall.platform.modules.system.mapper.SysFileMapper;
import com.yxmall.platform.modules.system.service.SysFileService;
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
@Service("sysFileService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile> implements SysFileService {

    @Override
    public PageUtils getSysFileListPage(Map<String, Object> params) {
        IPage<SysFile> page = baseMapper.selectPage(
                new Query<SysFile>(params).getPage(),
                new QueryWrapper<SysFile>().lambda().like(SysFile::getOldFileName,params.get("oldFileName"))
        );
        return new PageUtils(page);
    }

}
