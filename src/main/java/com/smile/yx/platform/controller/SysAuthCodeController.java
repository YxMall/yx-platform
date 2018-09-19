package com.smile.yx.platform.controller;

import com.smile.yx.platform.service.SysAuthCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @description: 验证码接口
 * @author: qing.wang.o
 * @create: 2018-09-14 13:58
 **/
@RestController
@RequestMapping("code")
@Api(value = "验证码接口", description = "包含图形验证码短信验证码等")
public class SysAuthCodeController {

    @Autowired
    private SysAuthCodeService sysAuthCodeService;

    @GetMapping("image.jpg")
    @ApiOperation(value = "验证码", notes = "获取验证码")
    public void captcha(HttpServletResponse response, String uuid) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //获取图片验证码
        BufferedImage image = sysAuthCodeService.getImageCode();
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

}
