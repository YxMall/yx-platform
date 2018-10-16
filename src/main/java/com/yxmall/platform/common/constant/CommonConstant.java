package com.yxmall.platform.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @description: 通用的常亮
 * @author: qing.wang.o
 * @create: 2018-09-27 13:25
 **/
public interface CommonConstant {


    /**
     * 超级管理员id
     */
    Long SUPER_ADMIN_ID = 1L;
    /**
     * 用户正常状态
     * 1:正常 0 :禁用
     */
    Integer USER_NORMAL_STATUS = 1;


    @AllArgsConstructor
    public enum Message {
        ADD_SUCCESS("添加成功"),
        ADD_ERROR("添加失败"),
        EDIT_SUCCESS("修改成功"),
        EDIT_ERROR("修改失败"),
        DEL_SUCCESS("删除成功"),
        DEL_ERROR("删除失败");
        @Getter
        private String msg;
    }


    /**
     * 菜单枚举类
     */
    @AllArgsConstructor
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);


        @Getter
        private int value;

    }


}
