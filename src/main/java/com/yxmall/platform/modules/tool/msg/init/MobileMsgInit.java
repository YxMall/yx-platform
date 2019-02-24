package com.yxmall.platform.modules.tool.msg.init;

import com.ulisesbocchio.jasyptspringboot.annotation.ConditionalOnMissingBean;
import com.yxmall.platform.common.utils.JsonUtils;
import com.yxmall.platform.common.validator.group.AddGroup;
import com.yxmall.platform.common.validator.group.UpdateGroup;
import com.yxmall.platform.modules.system.entity.SysConfig;
import com.yxmall.platform.modules.system.service.SysConfigService;
import com.yxmall.platform.modules.tool.msg.config.MobileConfig;
import com.yxmall.platform.modules.tool.msg.constants.MobileMsgConstant;
import com.yxmall.platform.modules.tool.msg.service.MobileSmsService;
import com.yxmall.platform.modules.tool.msg.service.impl.QcloudMobileSmsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;
import sun.rmi.runtime.Log;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * @description: 短信对象初始创建
 * @author: qing.wang.o
 * @create: 2019-01-26 23:00
 **/
@Configuration
//当某个bean加载完才执行
@DependsOn(value = "sysConfigService")
@Slf4j
public class MobileMsgInit {

    @Autowired
    private SysConfigService sysConfigService;


    /**
     * 根据条件创建bean
     *
     * @return
     * @throws Exception
     */
    @Bean
    public MobileSmsService createMobileSmsService() throws Exception {
        SysConfig config = sysConfigService.getSysConfigByKey(MobileMsgConstant.MOBILE_MSG_CONFIG_KEY);
        Optional<String> configValue = Optional.ofNullable(config).map(SysConfig::getConfigValue);
        if (!configValue.isPresent()) {
            log.warn("not found msg configInfo，not create mobileSmsService 。");
            return null;
        }
        MobileConfig mobileConfig = JsonUtils.jsonToObj(configValue.get(), MobileConfig.class);
        QcloudMobileSmsServiceImpl mobileSmsService = null;
        if (mobileConfig.getMobileType().equals(MobileMsgConstant.QCLOUD_MSG_TYPE)) {
            mobileSmsService = new QcloudMobileSmsServiceImpl(mobileConfig);
        }
        return mobileSmsService;
    }


}



