package com.yxmall.platform.modules.tool.oss.storage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: Oss配置
 * @author: qing.wang.o
 * @create: 2018-10-18 13:53
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OssStorageConfig {


    /**
     * oss地址
     */
    private String endpoint;

    /**
     * accessKeyId
     */
    private String accessKeyId;

    /**
     * accessKeySecret
     */
    private String accessKeySecret;


    /**
     * 桶
     */
    private String bucketName;

}
