package com.yxmall.platform.security.validate.image;

import com.yxmall.platform.security.validate.ValidateCode;
import lombok.*;

import java.awt.image.BufferedImage;

/**
 * @description: 图形验证码
 * @author: qing.wang.o
 * @create: 2019-01-30 13:03
 **/
@Getter
@Setter
public class ImageCode extends ValidateCode {

    /**
     * 图形验证码
     */
    private BufferedImage image;

    public ImageCode(String id, BufferedImage image, String code, int expireIn) {
        super(id, code, expireIn);
        this.image = image;
    }
}
