package com.yxmall.platform.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author smalljop
 * @email 250543222@qq.com
 * @date 20190124 16:17:32
 */
@Data
@TableName("sys_file")
@NoArgsConstructor
@AllArgsConstructor
public class SysFile implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;
    /**
     * 旧文件名
     */
    private String oldFileName;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件存储key
     */
    private String path;

    /**
     * 地址
     */
    private String url;
    /**
     * 文件大小
     */
    private Long fileSize;
    /**
     * 创建用户
     */
    private Long createUser;
    /**
     * 创建时间
     */
    private Date createTime;

}