package com.yxmall.platform.modules.system.vo;

import lombok.Data;

import java.util.List;

/**
 * @description: 菜单
 * @author: qing.wang.o
 * @create: 2018-09-28 10:25
 **/
@Data
public class MenuVO {

    private String title;

    private String icon;

    private List<MenuVO> children;

}
