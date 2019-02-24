package com.yxmall.platform.common.constant;

/**
 * @description: redis key名常量  参考阿里云redis使用规范
 * @author: qing.wang.o
 * @create: 2019-01-28 16:11
 **/
public interface RedisKeyConstant {


    /**
     * 短信发送数量
     */
    String MOBILE_CODE_SEND_COUNT = "plat:mobile:ip:";


    /**
     * 验证码前缀
     */
    String VALIDATE_CODE_PREFIX = "plat:validate:code:";
}
