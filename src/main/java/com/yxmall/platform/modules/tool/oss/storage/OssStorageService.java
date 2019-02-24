package com.yxmall.platform.modules.tool.oss.storage;

import com.yxmall.platform.common.utils.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
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
     * 文件上传
     *
     * @param file 文件 上传路径
     * @param path
     * @return
     */
    public abstract String upload(MultipartFile file, String path);


    /**
     * 下载文件
     *
     * @param path 相对于存储系统的路径
     * @return
     */
    public abstract InputStream download(String path);

    /**
     * 删除文件
     *
     * @param path 相对于存储系统的路径
     * @return
     */
    public abstract void delete(String path);
}
