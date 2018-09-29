package com.yxmall.platform.modules.system.service;

import com.yxmall.platform.common.utils.Result;

import java.awt.image.BufferedImage;

/**
 * @description: 验证码
 * @author: qing.wang.o
 * @create: 2018-09-14 13:54
 **/
public interface SysAuthCodeService {

    /**
     * 获取图形验证码
     *
     * @param uuid
     * @return
     */
    BufferedImage getImageCode(String uuid);


    /**
     * 发送手机验证码
     *
     * @param mobile
     * @return Result
     */
    Result sendMobileMsgCode(String mobile);
}
