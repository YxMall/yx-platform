package com.yxmall.platform.modules.oa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxmall.platform.modules.oa.entity.Notify;
import com.yxmall.platform.modules.oa.entity.NotifyRecord;
import com.yxmall.platform.modules.oa.vo.NotifyRecordVo;
import com.yxmall.platform.modules.oa.vo.NotifyVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @description: 通知记录
 * @author: qing.wang.o
 * @create: 2018-12-14 11:36
 **/
@Mapper
public interface NotifyRecordMapper extends BaseMapper<NotifyRecord> {


    /**
     * 分页查询
     *
     * @param page
     * @param params
     * @return
     */
    IPage<NotifyRecordVo> selectNotifyRecordPage(Page page,@Param("params") Map<String, Object> params);

    /**
     * 获取通知详情
     * @return
     * @param id
     */
    NotifyVo selectNotifyDetail(@Param("id") Long id);
}
