package com.yxmall.platform.modules.tool.oss.storage;


/**
 * @description: oss对象存储工厂
 * @author: qing.wang.o
 * @create: 2018-10-18 13:43
 **/
public class OssStorageFactory {

    static OssStorageConfig ossConfig;

    static {
        ossConfig = new OssStorageConfig();
        ossConfig.setAccessKeySecret("okIHeSC7qFftR9FKsEzL7OyA9EVF7L");
        ossConfig.setAccessKeyId("LTAIqRHCKGq7maHb");
        ossConfig.setBucketName("superadmin");
        ossConfig.setEndpoint("oss-cn-beijing.aliyuncs.com");
    }

    public static OssStorageService build() {
        return new AliyunOssStorageService(ossConfig);
    }

}
