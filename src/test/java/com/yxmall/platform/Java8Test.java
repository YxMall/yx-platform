package com.yxmall.platform;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @description:
 * @author: qing.wang.o
 * @create: 2019-01-24 10:09
 **/
public class Java8Test {

    public static void main(String[] args) {
        List<String> strings = Lists.newArrayList("1", "2", "3", "4", "5");
        strings.stream().limit(1).distinct().forEach(System.out::println);
    }

    @Test
    public void flatMap() {
        List<Map<String, Map<String, String>>> list = Lists.newArrayList();
        list.add(ImmutableMap.of("1", ImmutableMap.of("1", "张三")));
        list.add(ImmutableMap.of("2", ImmutableMap.of("2", "李四")));

//        List<String> string = list.stream().flatMap((m) -> {
//            return m.values().stream();
//        }).collect(Collectors.toList());


        List<String> collect = list.stream().flatMap((m) -> {
            return m.values().stream();
        }).flatMap((m1) -> {
            return m1.values().stream();
        }).collect(Collectors.toList());
        collect.forEach(System.out::println);


    }

}
