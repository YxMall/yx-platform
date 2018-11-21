package com.yxmall.platform.modules.oa.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.util.HtmlUtils;

/**
 * @description: 测试webscoket
 * @author: qing.wang.o
 * @create: 2018-11-06 16:38
 **/
public class TestController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(String msg) throws Exception {
        return msg;
    }
}
