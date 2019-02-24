package com.yxmall.platform.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.modules.system.entity.SysDictIndex;

import java.util.Map;

/**
 * 数据字典索引表
 *
 * @author smalljop
 * @email 250543222@qq.com
 * @date 20181229 10:19:44
 */
public interface SysDictIndexService extends IService<SysDictIndex> {


    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    PageUtils getDictIndexListPage(Map<String, Object> params);

}
