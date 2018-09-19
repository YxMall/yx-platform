package com.smile.yx.platform.service.impl;

import com.google.code.kaptcha.Producer;
import com.smile.yx.platform.service.SysAuthCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

/**
 * @description:
 * @author: qing.wang.o
 * @create: 2018-09-14 13:57
 **/
@Service
public class SysAuthCodeServiceImpl implements SysAuthCodeService {

    @Autowired
    private Producer producer;

    @Override
    public BufferedImage getImageCode() {
        //生成文字验证码
        String code = producer.createText();
        return producer.createImage(code);
    }
}
