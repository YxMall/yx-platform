package com.yxmall.platform.security.validate.mobile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.bcel.internal.classfile.Code;
import com.yxmall.platform.common.utils.JsonUtils;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.common.xss.XssHttpServletRequestWrapper;
import com.yxmall.platform.modules.tool.msg.service.MobileSmsService;
import com.yxmall.platform.security.exception.GeneratorCodeException;
import com.yxmall.platform.security.exception.ValidateCodeException;
import com.yxmall.platform.security.validate.AbstractValidateCodeProcessor;
import com.yxmall.platform.security.validate.ValidateCode;
import com.yxmall.platform.security.validate.image.ImageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @description: 手机短信验证处理器
 * @author: qing.wang.o
 * @create: 2019-01-30 13:57
 **/
@Component("mobileValidateCodeProcessor")
public class MobileCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private MobileSmsService mobileSmsService;


    @Override
    public void send(ServletWebRequest request, ValidateCode validateCode) {
        Result result = mobileSmsService.sendCodeMsg(validateCode.getCode(), validateCode.getId());
        if (result.isError()) {
            throw new GeneratorCodeException("发送失败");
        }
    }

    @Override
    public void validate(ServletWebRequest request) throws IOException {
        ObjectMapper instance = JsonUtils.getInstance();
        JsonNode params = instance.readTree(((XssHttpServletRequestWrapper) request.getRequest()).getBody());
        if (null == params) {
            throw new ValidateCodeException("请填写验证信息");
        } else {
            this.validateCode(params.findPath("code").asText(), params.findPath("mobile").asText());
        }
    }
}
