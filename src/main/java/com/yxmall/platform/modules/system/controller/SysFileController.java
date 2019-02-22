package com.yxmall.platform.modules.system.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.common.utils.ResponseUtil;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.common.validator.ValidatorUtils;
import com.yxmall.platform.common.validator.group.AddGroup;
import com.yxmall.platform.common.validator.group.UpdateGroup;

import com.yxmall.platform.modules.tool.oss.constants.StorageConstant;
import com.yxmall.platform.modules.tool.oss.storage.OssStorageFactory;
import com.yxmall.platform.modules.tool.oss.storage.OssStorageService;
import com.yxmall.platform.modules.tool.oss.utils.StorageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;


import com.yxmall.platform.modules.system.entity.SysFile;
import com.yxmall.platform.modules.system.service.SysFileService;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletResponse;


/**
 * @author smalljop
 * @email 250543222@qq.com
 * @date 20190124 16:17:32
 */
@RestController
@RequestMapping("/sys/file")
public class SysFileController extends AbstractController {
    @Autowired
    private SysFileService sysFileService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @ApiOperation(value = "获取文件信息列表", notes = "获取文件信息列表分页信息")
    public PageUtils getSysFileListPage(@RequestParam Map<String, Object> params) {
        return sysFileService.getSysFileListPage(params);
    }


    @PostMapping("upload")
    @ApiOperation(value = "文件上传", notes = "上传文件，返回预览地址")
    public Result upload(@RequestParam MultipartFile file) throws IOException {
        //获取文件后缀
        String originalFilename = file.getOriginalFilename();
        String fileSuffixName = StorageUtils.getFileSuffixNamePoint(originalFilename);
        String path = StorageUtils.generateFileName(StorageConstant.FILE_UPLOAD_PREFIX, fileSuffixName);
        String result = OssStorageFactory.build().upload(file, path);
        //新文件名
        String fileName = StorageUtils.getFileName(path);
        SysFile sysFile = new SysFile(null, originalFilename, fileName, file.getContentType(), path, result, file.getSize(), getCurrentUserId(), new Date());
        boolean save = sysFileService.save(sysFile);
        return Result.success(save);
    }

    /**
     * 下载文件
     */
    @GetMapping(value = "/download")
    @ApiOperation(value = "下载文件", notes = "根据path下载文件")
    public void download(String path, String fileName, HttpServletResponse response) throws FileNotFoundException {
        InputStream inputStream = OssStorageFactory.build().download(path);
        ResponseUtil.outFile(response, inputStream, fileName);
    }


    /**
     * 删除
     */
    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "删除", notes = "根据ID删除")
    public Result deleteSysFile(@RequestBody Map<String,Object> params) throws JSONException {
        OssStorageFactory.build().delete(params.get("path").toString());
        return Result.isSuccess(sysFileService.removeById(Long.parseLong(params.get("id").toString())));
    }

}
