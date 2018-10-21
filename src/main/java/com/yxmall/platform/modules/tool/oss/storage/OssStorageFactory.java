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
//        ossConfig.setAccessKeySecret("okIHeSC7qFftR9FKsEzL7OyA9EVF7L");
//        ossConfig.setAccessKeyId("LTAIqRHCKGq7maHb");
//        ossConfig.setBucketName("superadmin");
//        ossConfig.setEndpoint("oss-cn-beijing.aliyuncs.com");
        ossConfig.setAccessKeyId("AKIDVvDLH6NJdlhRq8JH5qRWUHtKZcWFo6HH");
        ossConfig.setAccessKeySecret("whIhuwnhv8D7pMghW9ct31nNlxyqX2LT");
        ossConfig.setBucketName("document-1252869644");
        ossConfig.setEndpoint("ap-shanghai");
    }

    public static OssStorageService build() {
        return new QloudOssStorageService(ossConfig);
    }

}
