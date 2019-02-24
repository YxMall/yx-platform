package com.yxmall.platform.security.validate.mobile;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ReUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.yxmall.platform.common.constant.RedisKeyConstant;
import com.yxmall.platform.common.utils.IpAddressUtils;
import com.yxmall.platform.common.utils.JsonUtils;
import com.yxmall.platform.common.utils.RedisUtils;
import com.yxmall.platform.common.xss.XssHttpServletRequestWrapper;
import com.yxmall.platform.security.exception.GeneratorCodeException;
import com.yxmall.platform.security.exception.ValidateCodeException;
import com.yxmall.platform.security.validate.ValidateCode;
import com.yxmall.platform.security.validate.ValidateCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * @description: 图形验证码生成器
 * @author: qing.wang.o
 * @create: 2019-01-30 13:05
 **/
@Slf4j
@Component("mobileValidateCodeGenerator")
public class MobileCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private RedisUtils redisUtils;


    @Override
    public ValidateCode generate(ServletWebRequest request) throws IOException {
        //包装request 解决request只能读取一次的问题
        JsonNode params = JsonUtils.getInstance().readTree(((XssHttpServletRequestWrapper) request.getRequest()).getBody());
        String mobile = params.get("mobile").textValue();
        if (StringUtils.isBlank(mobile)) {
            throw new ValidateCodeException("手机号码不能为空");
        }
        boolean flag = ReUtil.isMatch("^(13[0-9]|14[0-9]|15[0-9]|166|17[0-9]|18[0-9]|19[8|9])\\d{8}$", mobile);
        if (!flag){
            throw new ValidateCodeException("手机格式不正确");
        }
        //限制短信轰炸 一个Ip地址每天只能请求三次
        String ipAddr = IpAddressUtils.getIpAddr(request.getRequest());
        String key = RedisKeyConstant.MOBILE_CODE_SEND_COUNT + ipAddr;
        if (redisUtils.exists(key)) {
            redisUtils.incr(key, 1);
        } else {
            redisUtils.set(key, 1, RedisUtils.DEFAULT_EXPIRE);
        }
        //发送次数
        Integer count = redisUtils.get(key, Integer.class);
        if (count > 3) {
            throw new GeneratorCodeException("发送次数超出限制");
        }
        //判断是否间隔5分钟
        if (redisUtils.exists(RedisKeyConstant.VALIDATE_CODE_PREFIX + mobile)) {
            throw new GeneratorCodeException("发送速度太快了，请稍后再试");
        }
        String code = RandomStringUtils.randomNumeric(4);
        log.info("mobile:{},code:{}", mobile, code);
        return new ValidateCode(mobile, code, defaultExpireTime);
    }


}
