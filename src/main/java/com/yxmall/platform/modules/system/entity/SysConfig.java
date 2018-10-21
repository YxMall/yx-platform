/**
 * author:smile
 * Date:2018/10/21
 * Time:下午4:35
 */
package com.yxmall.platform.modules.system.entity;

import lombok.Data;

/**
 * 系统配制
 * @author:smile
 * Date:2018/10/21
 * Time:下午4:35
 *
 * */
@Data
public class SysConfig {

    private Long id;

    private String paramKey;

    private String paramValue;


    private Integer status;


    private String remark;


}
