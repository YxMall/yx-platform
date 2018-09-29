package com.yxmall.platform.modules.system.controller;

import com.yxmall.platform.common.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: qing.wang.o
 * @create: 2018-09-26 11:07
 **/
@RestController
public class TestController {

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("/test")
    public String test() {
        redisUtils.set("这啊", "ss");
        return "ss";
    }
}
