package com.yxmall.platform.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典索引表
 *
 * @author smalljop
 * @email 250543222@qq.com
 * @date 20181229 10:21:55
 */
@Data
@TableName("sys_dict_data")
public class SysDictData implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 字典数据id
     */
    @TableId
    private Integer dataId;

    /**
     * 字典数据键
     */
    private String dataKey;
    /**
     * 字典数据值
     */
    private String dataValue;
    /**
     * 字典数据代码  来源索引表
     */
    private String dictCode;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 备注
     */
    private String remark;

}