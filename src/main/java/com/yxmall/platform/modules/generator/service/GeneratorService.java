package com.yxmall.platform.modules.generator.service;

import com.yxmall.platform.modules.generator.mapper.GeneratorMapper;
import com.yxmall.platform.modules.generator.utils.GenUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * @Description:代码生成
 * @Author: wangqing
 * @CreateDate: 2018/6/25 17:23
 * @Version: 1.0
 */
@Service
public class GeneratorService {

    @Autowired
    private GeneratorMapper generatorMapper;

    /**
     * 查询表数据
     *
     * @param params
     * @return
     */
    public List<Map<String, Object>> queryList(Map<String, Object> params) {
        return generatorMapper.queryList(params);
    }

    public int queryTotal(Map<String, Object> params) {
        return generatorMapper.queryTotal(params);
    }

    public byte[] generatorCode(String... tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        for (String tableName : tableNames) {
            //查询表信息
            Map<String, String> table = queryTable(tableName);
            //查询列信息
            List<Map<String, String>> columns = queryColumns(tableName);
            //生成代码
            GenUtils.generatorCode(table, columns, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    private List<Map<String, String>> queryColumns(String tableName) {
        return generatorMapper.queryColumns(tableName);
    }

    private Map<String, String> queryTable(String tableName) {
        return generatorMapper.queryTable(tableName);
    }
}
