package com.smile.yx.platform.common.security;

import com.smile.yx.platform.common.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: qing.wang.o
 * @create: 2018-09-21 15:36
 **/
@RestController
public class SecurityController {


    @GetMapping("/auth/needLogin")
    public Result needLogin() {
        return Result.error(401, "请进行登录");
    }
}
