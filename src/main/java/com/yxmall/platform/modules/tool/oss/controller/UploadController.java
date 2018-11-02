package com.yxmall.platform.modules.tool.oss.controller;

import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.modules.tool.oss.constants.OssConstant;
import com.yxmall.platform.modules.tool.oss.storage.OssStorageFactory;
import com.yxmall.platform.modules.tool.oss.storage.OssStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 文件上传
 * @author: qing.wang.o
 * @create: 2018-10-23 10:14
 **/
@RestController
@RequestMapping("oss")
@Api(value = "文件上传接口", description = "包含通用上传接口到OSS存储")
public class UploadController {

    @PostMapping("upload")
    @ApiOperation(value = "文件上传", notes = "上传文件，返回预览地址")
    public Result upload(@RequestParam  MultipartFile file) throws IOException {
        //生成随机文件名
        //获取文件后缀
        String fileSuffixName = OssStorageService.getFileSuffixName(file.getOriginalFilename());
        String path = OssStorageService.generateFileName(OssConstant.FILE_UPLOAD_PREFIX, fileSuffixName);
        String result = OssStorageFactory.build().upload(file, path);
        return Result.success("上传成功", result);

    }


}












