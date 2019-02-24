package com.yxmall.platform.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxmall.platform.common.utils.PageUtils;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.modules.system.entity.SysFile;

import java.util.Map;

/**
 * @author smalljop
 * @email 250543222@qq.com
 * @date 20190124 16:17:32
 */
public interface SysFileService extends IService<SysFile> {

    /**
     * 获取分页信息
     *
     * @param params
     * @return
     */
    PageUtils getSysFileListPage(Map<String, Object> params);

}
