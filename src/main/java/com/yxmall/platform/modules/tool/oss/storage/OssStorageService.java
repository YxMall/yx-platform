package com.yxmall.platform.modules.tool.oss.storage;

import com.yxmall.platform.common.utils.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
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
     * 生成文件名 包含路径 防止重复
     *
     * @param prefix 前缀
     * @param suffix 后缀
     * @return
     */
    public static String generateFileName(String prefix, String suffix) {
        //生成uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //文件路径
        String path = TimeUtils.parseTime(LocalDateTime.now(), TimeUtils.TimeFormat.SHORT_DATE_PATTERN_NONE) + "/" + uuid;
        if (StringUtils.isNotBlank(prefix)) {
            path = prefix + '/' + path;
        }
        return path + suffix;
    }


    /**
     * 获取文件名后缀 包含.
     *
     * @param fileName 文件
     * @return
     */
    public static String getFileSuffixName(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        return suffix;
    }


    /**
     * 文件上传
     *
     * @param file 文件 上传路径
     * @param path
     * @return
     */
    public abstract String upload(MultipartFile file, String path);
}
