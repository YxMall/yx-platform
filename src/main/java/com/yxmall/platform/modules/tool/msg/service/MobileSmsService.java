package com.yxmall.platform.modules.tool.msg.service;

import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.modules.system.entity.SysConfig;
import com.yxmall.platform.modules.tool.msg.config.MobileConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 短信发送接口
 * @author: qing.wang.o
 * @create: 2019-01-26 22:30
 **/
public abstract class MobileSmsService {
    /**
     * 短信配置对象
     */
    public MobileConfig mobileConfig;


    /**
     * 发送验证码
     * 默认配置中模板id即是验证码模板id 发送验证码只需要动态输入验证码即可
     *
     * @param code  验证码
     * @param phone 手机号码
     * @return
     */
    public abstract Result sendCodeMsg(String code, String phone);

    /**
     * 发送验证码
     * 默认配置中模板id即是验证码模板id 发送验证码只需要动态输入验证码即可
     *
     * @param code       验证码
     * @param templateId 自定义模板id
     * @param phone      手机号码
     * @return
     */
    public abstract Result sendMsg(String code, Integer templateId, String phone);
}
