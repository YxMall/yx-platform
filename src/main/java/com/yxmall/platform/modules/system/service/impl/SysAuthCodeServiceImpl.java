package com.yxmall.platform.modules.system.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.google.code.kaptcha.Producer;
import com.yxmall.platform.common.template.MobileMsgTemplate;
import com.yxmall.platform.common.utils.RedisUtils;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.common.utils.SendMobileSmsUtils;
import com.yxmall.platform.modules.system.service.SysAuthCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

/**
 * @description:
 * @author: qing.wang.o
 * @create: 2018-09-14 13:57
 **/
@Service
public class SysAuthCodeServiceImpl implements SysAuthCodeService {

//    public static final JSONObject CONTENT = new JSONObject();
    @Autowired
    private Producer producer;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private SendMobileSmsUtils smsUtils;

    /**
     * 验证码过期时间 统一5分钟
     */
    private static final Long expireTime = 300L;

    @Override
    public BufferedImage getImageCode(String uuid) {
        //生成文字验证码
        String code = producer.createText();
        //保存到redis 5分钟过期
        redisUtils.set(uuid, code, expireTime);
        return producer.createImage(code);
    }

    @Override
    public Result sendMobileMsgCode(String mobile) {
//        //生成验证码
//        try {
//            String mobileCode = smsUtils.getFourRandom();
////            JSONObject jsonObject = new JSONObject();
////            jsonObject.put("code", mobileCode);
//            //保存到redis
//            MobileMsgTemplate template = new MobileMsgTemplate(mobile, jsonObject.toString(), "", "SMS_146290969");
//            boolean flag = smsUtils.sendSmsCode(template);
//            if (flag) {
//                redisUtils.set(mobile, mobileCode);
//                return Result.success("发送成功");
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
        return Result.error();
    }
}
