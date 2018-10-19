package com.yxmall.platform.modules.tool.oss.storage;

import com.yxmall.platform.common.utils.TimeUtils;
import org.apache.commons.lang3.time.DateUtils;
import java.io.File;
import java.io.InputStream;
import java.util.UUID;

/**
 * @description: Oss基础客户端
 * @author: qing.wang.o
 * @create: 2018-10-18 13:57
 **/
public abstract class OssStorageService {

    /**
     * oss配置
     */
    OssStorageConfig config;

    /**
     * 生成文件名 防止重复
     *
     * @param prefix
     * @param suffix
     * @return
     */
    public String generateFileName(String prefix, String suffix) {
        //生成uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //文件路径
//        String path = DateUtils.for(new Date(), "yyyyMMdd") + "/" + uuid;
        return "";
    }


    /**
     * 文件上传
     *
     * @param inputStream 文件 上传路径
     * @param path
     * @return
     */
    public abstract String upload(InputStream inputStream, String path);
}
