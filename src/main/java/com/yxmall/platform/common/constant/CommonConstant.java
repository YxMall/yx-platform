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
