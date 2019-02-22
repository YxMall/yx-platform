package com.yxmall.platform.modules.tool.oss.storage;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import com.yxmall.platform.common.exception.BaseException;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @description: 腾讯云OSS
 * @author: qing.wang.o
 * @create: 2018-10-18 14:01
 **/
public class QCloudOssStorageService extends OssStorageService {

    private COSClient client;

    public QCloudOssStorageService(OssStorageConfig config) {
        this.config = config;
        //初始化
        init();
    }

    private void init() {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(config.getAccessKeyId(), config.getAccessKeySecret());
        // 2 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(config.getRegion()));
        client = new COSClient(cred, clientConfig);
    }

    @Override
    public String upload(MultipartFile file, String path) {
        //路径前面需要/
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            // 从输入流上传必须制定content length, 否则http客户端可能会缓存所有数据，存在内存OOM的情况
            objectMetadata.setContentLength(file.getSize());
            // 默认下载时根据cos路径key的后缀返回响应的contenttype, 上传时设置contenttype会覆盖默认值
            objectMetadata.setContentType(file.getContentType());
            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(config.getBucketName(), path, file.getInputStream(), objectMetadata);
            // 设置存储类型, 默认是标准(Standard), 低频(standard_ia)
            putObjectRequest.setStorageClass(StorageClass.Standard);
            PutObjectResult putObjectResult = client.putObject(putObjectRequest);
            // putobjectResult会返回文件的etag
            String etag = putObjectResult.getETag();
        } catch (Exception e) {
            throw new BaseException("上传文件失败，请检查配置信息", e);
        }

        /**
         * 用户在所属地域广州创建了一个存储桶，存储桶名中用户自定义字符串部分为 example，系统自动为用户生成的数字串 APPID 为 1234567890，则其默认访问域名为：
         *
         *  example-1234567890.cos.ap-guangzhou.myqcloud.com
         */
        return config.getDomain() + path;

    }

    @Override
    public InputStream download(String path) {
        GetObjectRequest getObjectRequest = new GetObjectRequest(config.getBucketName(), path);
        COSObject object = client.getObject(getObjectRequest);
        COSObjectInputStream objectContent = object.getObjectContent();
        return objectContent;
    }

    @Override
    public void delete(String path) {
        // 指定文件所在的存储桶
        // 指定文件在 COS 上的对象键
        client.deleteObject(config.getBucketName(), path);
    }
}
