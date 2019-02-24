package com.yxmall.platform.security.validate;

import com.yxmall.platform.common.constant.RedisKeyConstant;
import com.yxmall.platform.common.utils.RedisUtils;
import com.yxmall.platform.security.exception.ValidateCodeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.util.Map;

/**
 * @description: 验证码处理器抽象实现
 * 暂无
 * @author: qing.wang.o
 * @create: 2019-01-30 11:26
 **/
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {


    @Autowired
    private RedisUtils redisUtils;

    /**
     * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现。
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    /**
     * 获取验证码存储key
     *
     * @param key
     * @return
     */
    public String getCodeKey(String key) {
        return RedisKeyConstant.VALIDATE_CODE_PREFIX + key;
    }

    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generateCode(request);
        save(request, validateCode);
        send(request, validateCode);
    }


    /**
     * 保存验证码
     *
     * @param request
     * @param validateCode
     */
    private void save(ServletWebRequest request, C validateCode) {
        redisUtils.set(getCodeRedisKey(validateCode.getId()), validateCode.getCode(), ValidateCodeGenerator.defaultExpireTime.longValue());
    }


    /**
     * 获取redis存储的验证码key
     *
     * @param key
     * @return
     */
    private static String getCodeRedisKey(String key) {
        return RedisKeyConstant.VALIDATE_CODE_PREFIX + key;
    }


    /**
     * 发送校验码，由子类实现
     *
     * @param request
     * @param validateCode
     * @throws Exception
     */
    public abstract void send(ServletWebRequest request, C validateCode) throws IOException;

    /**
     * 生成验证码
     *
     * @param request
     * @return
     */
    private C generateCode(ServletWebRequest request) throws IOException {
        ValidateCodeGenerator validateCodeGenerator = getValidateGenerate(request);
        return (C) validateCodeGenerator.generate(request);
    }

    /**
     * 获取验证码生成器
     *
     * @param request
     * @return
     * @throws IOException
     */
    private ValidateCodeGenerator getValidateGenerate(ServletWebRequest request) throws IOException {
        String type = getValidateCodeType(request).toString().toLowerCase();
        String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
        if (validateCodeGenerator == null) {
            throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
        }
        return validateCodeGenerator;
    }


    /**
     * 验证验证码是否正确
     *
     * @param request
     */
    @Override
    public abstract void validate(ServletWebRequest request) throws IOException,ValidateCodeException;


    /**
     * 对验证码进行基础验证
     */
    public void validateCode(String code, String key) throws ValidateCodeException {
        Object redisCode = redisUtils.get(getCodeRedisKey(key));
        if (StringUtils.isBlank(code)) {
            throw new ValidateCodeException("验证码不能为空");
        }
        if (null == redisCode) {
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equals(redisCode.toString(), code)) {
            throw new ValidateCodeException("验证码不正确");
        }
    }


    /**
     * 根据请求的url获取校验码的类型
     *
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }


}
