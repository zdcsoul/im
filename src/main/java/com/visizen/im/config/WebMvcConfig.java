package com.visizen.im.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.util.List;

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

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.clear();
        exceptionResolvers.add(gloExceptionHandler());
    }

    @Bean
    public ChatWebSocketHandler chatWebSocketHandler(){
        return new ChatWebSocketHandler();
    }
    @Bean
    public WebSocketHandShakeInterceptor webSocketHandShakeInterceptor(){
        return new WebSocketHandShakeInterceptor();
    }
    @Bean
    public GloExceptionHandler gloExceptionHandler(){
        return new GloExceptionHandler();
    }
}
