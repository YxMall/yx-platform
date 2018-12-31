package com.yxmall.platform.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yxmall.platform.common.validator.group.AddGroup;
import com.yxmall.platform.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典索引表
 *
 * @author smalljop
 * @email 250543222@qq.com
 * @date 20181229 10:19:44
 */
@Data
@TableName("sys_dict_index")
public class SysDictIndex implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long dictId;
    /**
     * 字典数据代码
     */
    @NotBlank(groups = {AddGroup.class, UpdateGroup.class},message = "字典代码不能为空")
    private String dictCode;
    /**
     * 字典数据标题
     */
    @NotBlank(groups = {AddGroup.class, UpdateGroup.class},message = "字典标题不能为空")
    private String dictTitle;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 备注
     */
    private String remark;

}