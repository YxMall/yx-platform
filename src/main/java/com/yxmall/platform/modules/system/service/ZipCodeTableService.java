package com.yxmall.platform.modules.system.service;

import com.yxmall.platform.modules.system.entity.ZipCodeTable;

import java.util.List;

/**
 * Created by Dell on 28/10/2018.
 */
public interface ZipCodeTableService {
    //查询第二个数据库 表的信息
    List<ZipCodeTable> select();
}
