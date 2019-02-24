package com.yxmall.platform.security.social.qq.api;


import com.yxmall.platform.common.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @description: QQ实体
 * @author: qing.wang.o
 * @create: 2019-02-03 17:33
 **/
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {


    /**
     * 获取openId
     */
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";


    /**
     * 获取用户信息
     */
    private static final String URL_GET_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";


    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;

        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        this.openId = StringUtils.substringBetween(result, "\"openid\":", "}");

    }


    private String appId;

    private String openId;

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USER_INFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        QQUserInfo qqUserInfo = JsonUtils.jsonToObj(result, QQUserInfo.class);
        return qqUserInfo;
    }
}
