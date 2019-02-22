//package com.yxmall.platform.modules.system.controller;
//
//import com.yxmall.platform.common.utils.Result;
//import com.yxmall.platform.modules.system.service.SysAuthCodeService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import javax.imageio.ImageIO;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.constraints.Pattern;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//
///**
// * @description: 验证码接口
// * @author: qing.wang.o
// * @create: 2018-09-14 13:58
// **/
//@RestController
//@RequestMapping("code")
//@Validated
//@Api(value = "验证码接口", description = "包含图形验证码短信验证码等")
//public class SysAuthCodeController {
//
//    @Autowired
//    private SysAuthCodeService sysAuthCodeService;
//
//    @GetMapping("image.jpg")
//    @ApiOperation(value = "图形验证码", notes = "获取图形验证码")
//    public void captcha(HttpServletResponse response, String uuid) throws ServletException, IOException {
//        response.setHeader("Cache-Control", "no-store, no-cache");
//        response.setContentType("image/jpeg");
//        //获取图片验证码
//        BufferedImage image = sysAuthCodeService.getImageCode(uuid);
//        ImageIO.write(image, "jpg", response.getOutputStream());
//    }
//
//
//    @PostMapping("mobileCode")
//    @ApiOperation(value = "短信验证码", notes = "获取短信验证码")
//    @ApiImplicitParam(name = "mobile", value = "手机号码")
//    public Result sendSms(@Pattern(regexp = "^(13[0-9]|14[0-9]|15[0-9]|166|17[0-9]|18[0-9]|19[8|9])\\d{8}$", message = "手机号码不正确")
//                              @RequestBody String mobile, HttpServletRequest request) {
//        Result result = sysAuthCodeService.sendMobileMsgCode(mobile,request);
//        return result;
//    }
//}
