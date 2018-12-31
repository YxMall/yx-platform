package com.yxmall.platform.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.modules.system.entity.SysDictData;
import com.yxmall.platform.modules.system.vo.DictVO;

import java.util.List;
import java.util.Map;

/**
 * 数据字典索引表
 *
 * @author smalljop
 * @email 250543222@qq.com
 * @date 20181229 10:21:55
 */
public interface SysDictDataService extends IService<SysDictData> {


    /**
     * 分页
     *
     * @param params
     * @return
     */
    PageUtils getDictDataListPage(Map<String, Object> params);

    /**
     * 获取全部字典数据
     * @return
     */
    List<DictVO> getAllDict();
}
