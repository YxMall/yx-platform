package com.smile.yx.platform.common.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @description: 验证码配置
 * @author: qing.wang.o
 * @create: 2018-09-14 13:39
 **/
@Configuration
public class KaptchaConfig {
    @Bean
    public DefaultKaptcha getDefaultKaptcha() {
        com.google.code.kaptcha.impl.DefaultKaptcha defaultKaptcha = new com.google.code.kaptcha.impl.DefaultKaptcha();
        Properties properties = new Properties();
//        properties.setProperty("kaptcha.border", "yes");
        properties.setProperty("kaptcha.border.color", "105,179,90");
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        properties.setProperty("kaptcha.image.width", "130");
        properties.setProperty("kaptcha.image.height", "47");
        properties.setProperty("kaptcha.textproducer.font.size", "40");
        properties.setProperty("kaptcha.textproducer.char.length", "6");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
