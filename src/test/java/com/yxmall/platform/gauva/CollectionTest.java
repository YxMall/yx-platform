package com.yxmall.platform.gauva;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import com.yxmall.platform.common.utils.JsonUtils;
import org.junit.Test;

/**
 * @description: guava集合测试类
 * @author: qing.wang.o
 * @create: 2019-01-15 16:39
 **/
public class CollectionTest {


    /**
     * bimap 要求value值唯一性 可以快速让value 和值反转
     */
    @Test
    public void biMapTest() {
        BiMap<String, Object> biMap = HashBiMap.create();
        biMap.put("1", "张三");
        biMap.put("2", "李四");
        biMap.put("3", "王五");
        biMap.put("3", "王五");
        System.out.println(JsonUtils.objToJson(biMap));
        System.out.println(JsonUtils.objToJson(biMap.inverse()));
    }
}
