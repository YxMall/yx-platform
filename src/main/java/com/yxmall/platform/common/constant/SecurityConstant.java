package com.yxmall.platform.common.constant;

/**
 * @author Exrickx
 */
public interface SecurityConstant {

    /**
     * token分割
     */
    String TOKEN_SPLIT = "Bearer ";

    /**
     * JWT签名加密key
     */
    String JWT_SIGN_KEY = "xboot";

    /**
     * token参数头
     */
    String HEADER = "accessToken";

    /**
     * 权限参数头
     */
    String AUTHORITIES = "authorities";

    /**
     * 用户选择JWT保存时间参数头
     */
    String SAVE_LOGIN = "saveLogin";


    /**
     * 登录地址
     */
    String LOGIN_URL = "/auth/login";

    /**
     * 没有登录返回的地址
     */
    String NO_LOGIN_URL = "/auth/require";

}
