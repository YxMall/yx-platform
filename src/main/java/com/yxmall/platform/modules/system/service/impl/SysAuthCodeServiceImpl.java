//package com.yxmall.platform.modules.system.service.impl;
//
//import com.google.code.kaptcha.Producer;
//import com.yxmall.platform.common.constant.RedisKeyConstant;
//import com.yxmall.platform.common.emuns.ResponseStatusEnum;
//import com.yxmall.platform.common.utils.IpAddressUtils;
//import com.yxmall.platform.common.utils.RedisUtils;
//import com.yxmall.platform.common.utils.Result;
//import com.yxmall.platform.modules.system.service.SysAuthCodeService;
//import com.yxmall.platform.modules.tool.msg.service.MobileSmsService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpServletRequest;
//import java.awt.image.BufferedImage;
//
///**
// * @description:
// * @author: qing.wang.o
// * @create: 2018-09-14 13:57
// **/
//@Service
//@Slf4j
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//public class SysAuthCodeServiceImpl implements SysAuthCodeService {
//
//    private final Producer producer;
//
//    private final RedisUtils redisUtils;
//
//    private final MobileSmsService mobileSmsService;
//    /**
//     * 验证码过期时间 统一5分钟
//     */
//    private static final Long expireTime = 300L;
//
//    @Override
//    public BufferedImage getImageCode(String uuid) {
//        //生成文字验证码
//        String code = producer.createText();
//        //保存到redis 5分钟过期
//        redisUtils.set(uuid, code, expireTime);
//        return producer.createImage(code);
//    }
//
//    @Override
//    public Result sendMobileMsgCode(String mobile, HttpServletRequest request) {
//        //限制短信轰炸 一个Ip地址每天只能请求三次
//        String ipAddr = IpAddressUtils.getIpAddr(request);
//        String key = RedisKeyConstant.MOBILE_CODE_SEND_COUNT + ipAddr;
//        if (redisUtils.exists(key)) {
//            redisUtils.incr(key, 1);
//        } else {
//            redisUtils.set(key, 1, RedisUtils.DEFAULT_EXPIRE);
//        }
//        //发送次数
//        Integer count = redisUtils.get(key, Integer.class);
//        if (count > 3) {
//            return Result.error("发送次数超出限制");
//        }
//        //判断是否间隔5分钟
//        if (redisUtils.exists(mobile)) {
//            return Result.error("发送速度太快了，请稍后再试");
//        }
//        //生成验证码
//        String code = RandomStringUtils.random(4, "0123456789");
////        Result result = mobileSmsService.sendCodeMsg(code, mobile);
////        if (result.isError()) {
////            return result;
////        }
//        log.info("mobile:{},code:{}", mobile, code);
//        //存入缓存
//        redisUtils.set(mobile, code, expireTime);
//        return Result.success();
//    }
//}
