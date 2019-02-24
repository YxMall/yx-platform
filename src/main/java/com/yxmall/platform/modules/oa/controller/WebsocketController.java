package com.yxmall.platform.modules.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @description:
 * @author: smalljop
 * @create: 2018-12-05 14:44
 **/
@RestController
public class WebsocketController {

    @Autowired
    SimpMessagingTemplate template;

//    @ResponseBody
//    @GetMapping("/chat")
//    public String handleChat( String msg) {
//        template.convertAndSendToUser("admin", "/queue/notifications",  "给您发来了消息：" + msg);
//        return "admin";
//    }
}
