package com.yxmall.platform.security.social.qq.connect;

import com.yxmall.platform.security.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @description: qq连接工产
 * @author: qing.wang.o
 * @create: 2019-02-03 22:50
 **/
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}
