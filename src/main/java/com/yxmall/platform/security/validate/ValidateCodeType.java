package com.yxmall.platform.security.validate;


import com.yxmall.platform.common.constant.SecurityConstant;

/**
 * 验证码类型枚举
 *
 * @author qing.wang.o
 */
public enum ValidateCodeType {

    /**
     * 短信验证码
     */
    MOBILE {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstant.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },
    /**
     * 图片验证码
     */
    IMAGE {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstant.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };

    /**
     * 校验时从请求中获取的参数的名字
     *
     * @return
     */
    public abstract String getParamNameOnValidate();

}
