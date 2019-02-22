package com.yxmall.platform.security.validate;

import com.yxmall.platform.common.constant.SecurityConstant;
import com.yxmall.platform.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 验证码
 * @author: qing.wang.o
 * @create: 2019-01-30 12:54
 **/
@RestController
public class ValidateCodeController {


    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * 创建验证码，根据验证码类型不同，调用不同的
     * {@link ValidateCodeProcessor}接口实现
     *
     * @param request
     * @param response
     * @param type
     * @throws Exception
     */
    @RequestMapping(SecurityConstant.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public Result createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)
            throws Exception {
        validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
        return Result.success();
    }
}
