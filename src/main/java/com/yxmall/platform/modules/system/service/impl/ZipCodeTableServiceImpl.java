package com.yxmall.platform.modules.system.service.impl;

import com.yxmall.platform.common.dataSource.TargetDataSource;
import com.yxmall.platform.modules.system.entity.ZipCodeTable;
import com.yxmall.platform.modules.system.mapper.ZipCodeTableMapper;
import com.yxmall.platform.modules.system.service.ZipCodeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dell on 28/10/2018.
 */
@Service
public class ZipCodeTableServiceImpl implements ZipCodeTableService {
    @Autowired
    private ZipCodeTableMapper zipCodeTableMapper;
    @Override
    @TargetDataSource(name="ds2")
    public List<ZipCodeTable> select() {
        List<ZipCodeTable> list=zipCodeTableMapper.select();
        return list;
    }
}
