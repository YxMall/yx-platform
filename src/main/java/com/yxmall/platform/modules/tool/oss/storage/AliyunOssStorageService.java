package com.yxmall.platform.modules.tool.oss.storage;

import com.aliyun.oss.OSSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.yxmall.platform.common.exception.BaseException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

/**
 * @description: 阿里云oss
 * @author: qing.wang.o
 * @create: 2018-10-18 14:01
 **/
public class AliyunOssStorageService extends OssStorageService {

    private OSSClient client;

    public AliyunOssStorageService(OssStorageConfig config) {
        this.config = config;
        //初始化
        init();
    }

    private void init() {
        client = new OSSClient(config.getEndpoint(), config.getAccessKeyId(),
                config.getAccessKeySecret());
    }

    @Override
    public String upload(MultipartFile file, String path) {
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());
            client.putObject(config.getBucketName(), path, file.getInputStream());
        } catch (Exception e) {
            throw new BaseException("上传文件失败，请检查配置信息", e);
        }

        return config.getDomain() + "/" + path;
    }
}
