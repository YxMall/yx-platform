package com.yxmall.platform.modules.tool.msg.config;

import lombok.Data;

/**
 * @description: 短信配置信息
 * @author: qing.wang.o
 * @create: 2019-01-26 22:56
 **/
@Data
public class MobileConfig {
    /**
     * 短信平台类型
     */
    private Integer mobileType;
    /**
     * 短信模板
     */
    private Integer templateId;
    /**
     * 签名
     */
    private String sign;
    /**
     * 短信应用ID
     */
    private Integer appId;
    /**
     * 短信应用key
     */
    private String appKey;
}
