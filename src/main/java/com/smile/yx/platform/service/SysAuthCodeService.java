package com.smile.yx.platform.service;

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
     * @return
     */
    BufferedImage getImageCode();

}
