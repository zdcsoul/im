package com.visizen.im.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by Administrator on 2017/8/8.
 */
@Configuration
@EnableWebSocket
public class WebMvcConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatWebSocketHandler(),"/webSocketIMServer")
                .addInterceptors(webSocketHandShakeInterceptor());
        registry.addHandler(chatWebSocketHandler(),"/webSocketIMServer/sockjs")
                .addInterceptors(webSocketHandShakeInterceptor())
                .withSockJS();
    }
    @Bean
    public ChatWebSocketHandler chatWebSocketHandler(){
        return new ChatWebSocketHandler();
    }
    @Bean
    public WebSocketHandShakeInterceptor webSocketHandShakeInterceptor(){
        return new WebSocketHandShakeInterceptor();
    }
}
