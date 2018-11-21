package com.yxmall.platform.modules.oa.config;

/**
 * @description: websocket配置类
 * @link https://spring.io/guides/gs/messaging-stomp-websocket/
 * @author: qing.wang.o
 * @create: 2018-11-06 16:34
 **/
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * 通过EnableWebSocketMessageBroker
 * 开启使用STOMP协议来传输基于代理(message broker)的消息,
 * 此时浏览器支持使用@MessageMapping 就像支持@RequestMapping一样。
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 订阅Broker名称 user点对点 topic广播即群发
        config.enableSimpleBroker("/user","/topic");
        // 全局(客户端)使用的消息前缀
        config.setApplicationDestinationPrefixes("/app");
        // 点对点使用的前缀 无需配置 默认/user
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 允许使用socketJs方式访问 即可通过http://IP:PORT/ws来和服务端websocket连接
        registry.addEndpoint("/endpointChat").withSockJS();
    }

}