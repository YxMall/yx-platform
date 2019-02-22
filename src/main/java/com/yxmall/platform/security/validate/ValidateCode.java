package com.yxmall.platform.security.validate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


/**
 * 验证码对象
 *
 * @author smalljop
 */
@Data
public class ValidateCode {


    /**
     * 标识请求对象  图形验证码为请求携带uuid  手机验证码为手机号码
     */
    private String id;

    /**
     * 验证码
     */
    private String code;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    public ValidateCode(String id, String code, int expireIn) {
        this.id = id;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }


}
