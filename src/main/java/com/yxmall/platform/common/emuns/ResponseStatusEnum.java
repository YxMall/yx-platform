package com.yxmall.platform.common.emuns;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.javassist.runtime.Inner;

/**
 * 响应状态码枚举类
 * author:smile
 * Date:2018/9/22
 * Time:下午10:37
 */

@AllArgsConstructor
@Getter
public enum ResponseStatusEnum {



    SUCCESS(200,"成功"),
    UNAUTHORIZED(401,"请进行登录"),
    PERMISSION_DENIED(403,"抱歉，您没有访问权限"),
    INNER_ERROR(500,"未知异常，请联系管理员");

    private Integer code;

    private String msg;

}
