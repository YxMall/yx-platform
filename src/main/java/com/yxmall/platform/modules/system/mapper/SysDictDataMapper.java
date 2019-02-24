package com.yxmall.platform.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxmall.platform.modules.system.entity.SysDictData;
import com.yxmall.platform.modules.system.vo.DictVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 数据字典索引表
 *
 * @author smalljop
 * @email 250543222@qq.com
 * @date 20181229 10:21:55
 */
@Mapper
public interface SysDictDataMapper extends BaseMapper<SysDictData> {

    /**
     * 获取全部字典数据
     *
     * @return
     */
    List<DictVO> selectAllDict();
}
