package com.yxmall.platform.modules.generator.controller;

import com.yxmall.platform.common.utils.JsonUtils;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.modules.generator.service.GeneratorService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Description:生成
 * @Author: wangqing
 * @CreateDate: 2018/6/25 14:43
 * @Version: 1.0
 */

@Controller
@RequestMapping("/generator")
public class GeneratorController {

    @Autowired
    private GeneratorService generatorService;

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params){
        //查询列表数据
        List<Map<String, Object>> list = generatorService.queryList(params);
        int total = generatorService.queryTotal(params);
        return Result.success("list", list).put("totalCount",total);
    }


    /**
     * 生成代码
     * @param request
     * @param response
     */
    @RequestMapping("/code")
    public void code(HttpServletRequest request, HttpServletResponse response,String tableName) throws Exception {

        byte[] data = generatorService.generatorCode(tableName);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"code.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }

}
