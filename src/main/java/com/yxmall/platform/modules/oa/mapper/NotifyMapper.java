package com.yxmall.platform.modules.oa.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxmall.platform.modules.oa.entity.Notify;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxmall.platform.modules.oa.vo.NotifyVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 通知
 *
 * @author smalljop
 * @email 250543222@qq.com
 * @date 20181204 14:40:44
 */
@Mapper
public interface NotifyMapper extends BaseMapper<Notify> {

    /**
     * 当前用户发送信息
     * @param page 分页对象,xml中可以从里面进行取值,传递参数 Page 即自动分页,必须放在第一位(你可以继承Page实现自己的分页对象)
     * @param params 参数
     * @return 分页对象
     */
    IPage<NotifyVo> selectCurrentSendList(Page page,@Param("params") Map<String,Object> params);

}
