//package com.yxmall.platform.security.social.qq.connect;
//
//import com.yxmall.platform.security.social.qq.api.QQ;
//import com.yxmall.platform.security.social.qq.api.QQUserInfo;
//import org.springframework.social.connect.ApiAdapter;
//import org.springframework.social.connect.ConnectionValues;
//import org.springframework.social.connect.UserProfile;
//
///**
// * @description: qq适配器
// * ApiAdapter<QQ> 泛型
// * @author: qing.wang.o
// * @create: 2019-02-03 22:45
// **/
//public class QQAdapter implements ApiAdapter<QQ> {
//
//    /**
//     * 测试连接是否通
//     *
//     * @param qq
//     * @return
//     */
//    @Override
//    public boolean test(QQ qq) {
//        // QQ默认为通
//        return true;
//    }
//
//    @Override
//    public void setConnectionValues(QQ api, ConnectionValues values) {
//        QQUserInfo userInfo = api.getUserInfo();
//        values.setDisplayName(userInfo.getNickname());
//        values.setImageUrl(userInfo.getFigureurl_qq_1());
//        //个人主页
//        values.setProfileUrl(null);
//        values.setProviderUserId(userInfo.getOpenId());
//    }
//
//
//    /**
//     * 绑定解绑
//     *
//     * @param api
//     * @return
//     */
//    @Override
//    public UserProfile fetchUserProfile(QQ api) {
//        return null;
//    }
//
//    @Override
//    public void updateStatus(QQ qq, String s) {
//        //更新某些状态 如微博
//    }
//}
