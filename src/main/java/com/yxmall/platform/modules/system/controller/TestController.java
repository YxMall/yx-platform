package com.yxmall.platform.modules.system.controller;

import com.yxmall.platform.common.utils.*;
import com.yxmall.platform.modules.system.entity.SysConfig;
import com.yxmall.platform.modules.system.service.SysConfigService;
import com.yxmall.platform.modules.tool.msg.service.MobileSmsService;
import com.yxmall.platform.modules.tool.oss.storage.OssStorageConfig;
import com.yxmall.platform.modules.tool.oss.storage.OssStorageFactory;
import com.yxmall.platform.modules.tool.oss.storage.OssStorageService;
import com.yxmall.platform.modules.tool.oss.utils.StorageUtils;
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

    @Autowired
    private RedisLockUtil redisLockUtil;

    @Autowired
    private MobileSmsService mobileSmsService;


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
        String fileSuffixName = StorageUtils.getFileSuffixName(file.getOriginalFilename());
        String result = OssStorageFactory.build().upload(file, StorageUtils.generateFileName("file", fileSuffixName));
        return Result.success(result);
    }


    @Autowired
    SimpMessagingTemplate template;

    @ResponseBody
    @GetMapping("/chat")
    public String handleChat(String msg) {
        return "admin";
    }

    @ResponseBody
    @GetMapping("/msg")
    public Result handleMsg(String msg) {
        Result result = mobileSmsService.sendCodeMsg("1111", "17521698619");
        return result;
    }

    @RequestMapping("/testLock")
    public String testLock(@RequestParam Integer id) {
        log.info("进入分布式锁测试方法：{}", id);
        //新增锁返回的密码 用于解锁
        String identifier = null;
        //锁名
        String lockKey = "USER_ID" + id;
        try {
            log.info("尝试获取redis分布式锁");
            identifier = redisLockUtil.lock(lockKey);
            log.info("获取redis锁成功，进行入库操作");
            Thread.sleep(3000);
            log.info("插入完成");
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            log.info("释放分布式锁");
            redisLockUtil.releaseLock(lockKey, identifier);
        }
        return "OK";
    }


    public static void main(String[] args) {
        int i = 9999;
        System.out.println((i + "").length());
    }

}
