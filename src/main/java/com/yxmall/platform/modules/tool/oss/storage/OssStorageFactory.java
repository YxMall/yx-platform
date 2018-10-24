package com.yxmall.platform.modules.tool.oss.storage;


import com.yxmall.platform.common.utils.JsonUtils;
import com.yxmall.platform.common.utils.SpringBeanUtils;
import com.yxmall.platform.modules.system.entity.SysConfig;
import com.yxmall.platform.modules.system.service.SysConfigService;
import com.yxmall.platform.modules.tool.oss.constants.OssConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: oss对象存储工厂
 * @author: qing.wang.o
 * @create: 2018-10-18 13:43
 **/
@Slf4j
public class OssStorageFactory {

    private static OssStorageConfig ossConfig;


    static {
        SysConfig config = SpringBeanUtils.getBean(SysConfigService.class).getSysConfigByKey("ossConfigKey");
        try {
            log.info("oss配置信息：{}",config.getConfigValue());
            ossConfig = JsonUtils.jsonToObj(config.getConfigValue(), OssStorageConfig.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 构建oss存储对象
     *
     * @return
     */
    public static OssStorageService build() {
        OssStorageService ossStorageService = null;
        //oss类型
        Integer ossType = ossConfig.getOssType();
        if (ossType.equals(OssConstant.ALIYUN_OSS)) {
            ossStorageService = new AliyunOssStorageService(ossConfig);
        } else if (ossType.equals(OssConstant.QCLOUD_OSS)) {
            ossStorageService = new QCloudOssStorageService(ossConfig);
        }
        return ossStorageService;
    }

}
