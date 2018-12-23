package com.yxmall.platform.modules.oa.form;

import com.yxmall.platform.common.validator.group.AddGroup;
import com.yxmall.platform.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * @description: 通知表单对象
 * @author: qing.wang.o
 * @create: 2018-12-14 10:35
 **/
@Data
public class NotifyForm {

    @NotBlank(message = "通知id不能为空", groups = {UpdateGroup.class})
    private Long id;
    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String title;
    /**
     * 内容
     */
    @NotBlank(message = "内容不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String content;

    /**
     * 创建用户Id
     */
    private Long createUserId;

    /**
     * 接收用户的ID 如果为空则发送给全部用户
     */
    private List<Long> notifyUserIds;

}
