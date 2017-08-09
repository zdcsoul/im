package com.visizen.im.config;

import com.visizen.im.user.entity.User;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/8/8.
 */
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private static final List<WebSocketSession> sessions = Collections.synchronizedList(new ArrayList<>());

    private static final List<Long> inlineUserIds = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("客户端 " + session.getId() + " 已连接");
        HttpSession httpSession = (HttpSession)session.getAttributes().get("session");
        User user = (User)httpSession.getAttribute("user");
        inlineUserIds.add(user.getUserId());
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        session.sendMessage(message);
        System.out.println("客户端 "+ session.getId() + " 发来消息 " + message.getPayload());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("session " + session.getId() + " 异常，已关闭");
        if(session.isOpen()) session.close();
        HttpSession httpSession = (HttpSession)session.getAttributes().get("session");
        User user = (User)httpSession.getAttribute("user");
        inlineUserIds.remove(user.getUserId());
        sessions.remove(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("session " + session.getId() + " 正常关闭");
        HttpSession httpSession = (HttpSession)session.getAttributes().get("session");
        User user = (User)httpSession.getAttribute("user");
        inlineUserIds.remove(user.getUserId());
        sessions.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
