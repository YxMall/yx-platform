package com.yxmall.platform.security.validate.image;

import com.yxmall.platform.security.validate.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * @description:
 * @author: qing.wang.o
 * @create: 2019-01-30 13:57
 **/
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {
    @Override
    public void send(ServletWebRequest request, ImageCode imageCode) throws IOException {
        ImageIO.write(imageCode.getImage(), "jpg", request.getResponse().getOutputStream());
    }

    @Override
    public void validate(ServletWebRequest request) {
        String uuid = request.getParameter("uuid");
        String code = request.getParameter("code");
        this.validateCode(code, uuid);
    }
}
