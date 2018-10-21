package com.yxmall.platform.modules.system.controller;

import com.yxmall.platform.common.utils.RedisUtils;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.modules.tool.oss.storage.OssStorageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;

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


    @PostMapping("upload")
    public Result upload(MultipartFile file) throws IOException {
        OssStorageFactory.build().upload(file, "1.jpg");
        return Result.success();
    }
}
