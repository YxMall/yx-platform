package com.yxmall.platform.security.validate;

import com.yxmall.platform.security.exception.ValidateCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description: 验证码处理器持有者
 * @author: qing.wang.o
 * @create: 2019-01-30 11:11
 **/
@Component
public class ValidateCodeProcessorHolder {


    /**
     * 依赖查找 会把ValidateCodeProcessor 所有实现全部注入进来
     */
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    /**
     * 查找验证码处理器
     *
     * @param type
     * @return
     */
    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return this.findValidateCodeProcessor(type.toString().toLowerCase());
    }

    /**
     * @param type
     * @return
     */
    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        //默认bean name 类名首字母小写 查找出吧bean
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor processor = validateCodeProcessors.get(name);
        if (processor == null) {
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }
        return processor;
    }

}
