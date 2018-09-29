package com.yxmall.platform.common.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description: 短信模板
 * @author: qing.wang.o
 * @create: 2018-09-26 16:30
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MobileMsgTemplate implements Serializable {
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 组装后的模板内容JSON字符串
     */
    private String content;

    /**
     * 短信签名
     */
    private String signName;
    /**
     * 短信模板
     */
    private String template;

}
