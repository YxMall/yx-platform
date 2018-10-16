package com.yxmall.platform.modules.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description:  http://element-cn.eleme.io/#/zh-CN/component/tree
 * @author: qing.wang.o
 * @create: 2018-09-28 10:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElTreeVO {

    private Object id;

    private String label;

    private List<ElTreeVO> children;

}
