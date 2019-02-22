/**
 *
 */
package com.yxmall.platform.security.validate;

import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 验证码生成接口
 * @author smalljop
 *
 */
public interface ValidateCodeGenerator {


    /**
     * 验证码过期时间 统一5分钟
     */
    Integer defaultExpireTime = 300;

    /**
     * 生成验证码
     * @param request
     * @return
     */
    ValidateCode generate(ServletWebRequest request) throws IOException;


}
