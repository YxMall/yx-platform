package com.yxmall.platform.modules.system.vo;

import lombok.Data;

import java.util.List;

/**
 * @description: 字典业务对象
 * @author: qing.wang.o
 * @create: 2018-12-29 14:32
 **/
@Data
public class DictVO {
    /**
     * 字典类型
     */
    private String dictCode;
    /**
     * 字典具体数据
     */
    private List<DictDataVo> dictList;
}

@Data
class DictDataVo {
    /**
     * 字典key
     */
    private String dataKey;
    /**
     * 字典value
     */
    private String dataValue;
}