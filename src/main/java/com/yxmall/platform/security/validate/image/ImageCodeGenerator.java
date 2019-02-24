package com.yxmall.platform.security.validate.image;

import com.google.code.kaptcha.Producer;
import com.yxmall.platform.security.validate.ValidateCode;
import com.yxmall.platform.security.validate.ValidateCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.image.BufferedImage;

/**
 * @description: 图形验证码生成器
 * @author: qing.wang.o
 * @create: 2019-01-30 13:05
 **/
@Component("imageValidateCodeGenerator")
@Slf4j
public class ImageCodeGenerator implements ValidateCodeGenerator {


    @Autowired
    private Producer producer;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        //获取请求中的uuid
        String uuid = request.getParameter("uuid");
        String code = producer.createText();
        BufferedImage image = producer.createImage(code);
        log.info("uuid:{},code:{}", uuid, code);
        return new ImageCode(uuid, image, code, defaultExpireTime);
    }


}
