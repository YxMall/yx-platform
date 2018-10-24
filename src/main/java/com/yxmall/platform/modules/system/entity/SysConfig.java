/**
 * author:smile
 * Date:2018/10/21
 * Time:下午4:35
 */
package com.yxmall.platform.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.yxmall.platform.common.validator.group.AddGroup;
import com.yxmall.platform.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 系统配制
 *
 * @author:smile Date:2018/10/21
 * Time:下午4:35
 */
@Data
public class SysConfig {

    @TableId
    private Long configId;


    /**
     * 配置key
     */
    @NotBlank(message = "配置key不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String configKey;

    /**
     * 配置value
     */
    @NotBlank(message = "配置value不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String configValue;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 备注
     */
    private String remark;


    /**
     * 创建时间
     **/
    private Date createTime;

}
