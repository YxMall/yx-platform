package com.yxmall.platform.common.security.jwt;

import com.yxmall.platform.common.constant.SecurityConstant;
import com.yxmall.platform.common.emuns.ResponseStatusEnum;
import com.yxmall.platform.common.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: qing.wang.o
 * @create: 2018-09-21 15:36
 **/
@RestController
public class SecurityController {


    /**
     * springsecurity 没有登录返回
     *
     * @return
     */
    @GetMapping(SecurityConstant.NO_LOGIN_URL)
    public Result needLogin() {
        return Result.result(ResponseStatusEnum.UNAUTHORIZED);
    }
}
