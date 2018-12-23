package com.yxmall.platform.modules.system.controller;

import com.yxmall.platform.common.utils.JsonUtils;
import com.yxmall.platform.common.utils.RedisUtils;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.common.utils.SpringBeanUtils;
import com.yxmall.platform.modules.system.entity.SysConfig;
import com.yxmall.platform.modules.system.service.SysConfigService;
import com.yxmall.platform.modules.tool.oss.storage.OssStorageConfig;
import com.yxmall.platform.modules.tool.oss.storage.OssStorageFactory;
import com.yxmall.platform.modules.tool.oss.storage.OssStorageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @description:
 * @author: qing.wang.o
 * @create: 2018-09-26 11:07
 **/
@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RedisUtils redisUtils;


    public String test() {
        SysConfig config = SpringBeanUtils.getBean(SysConfigService.class).getSysConfigByKey("ossConfigKey");
        try {
            String json = JsonUtils.objToJson(config.getConfigValue());
            log.info(json);
            JsonUtils.jsonToObj(json, OssStorageConfig.class);
//            ossConfig = JsonUtils.jsonToObj(json, OssStorageConfig.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    @PostMapping("upload")
    public Result upload(MultipartFile file) throws IOException {
        String fileSuffixName = OssStorageService.getFileSuffixName(file.getOriginalFilename());
        String result = OssStorageFactory.build().upload(file, OssStorageService.generateFileName("file", fileSuffixName));
        return Result.success(result);
    }


    @Autowired
    SimpMessagingTemplate template;

    @ResponseBody
    @GetMapping("/chat")
    public String handleChat( String msg) {
        template.convertAndSendToUser("admin", "/queue/notifications",  "给您发来了消息：" + msg);
        return "admin";
    }

    public static void main(String[] args) {
        int i=9999  ;
        System.out.println((i+"").length());
    }

}
