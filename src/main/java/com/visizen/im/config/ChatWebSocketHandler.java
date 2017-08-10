package com.visizen.im.config;

import com.visizen.im.user.entity.User;
import com.visizen.im.utils.ChatTarget;
import com.visizen.im.utils.GsonUtils;
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
        User user = getCurrentUser(session);
        inlineUserIds.add(user.getUserId());
        sessions.add(session);
        System.out.println("客户端 " + user.getUsername() + " 已连接");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ChatTarget ct = GsonUtils.toBean(message.getPayload(),ChatTarget.class);
        if(Cts.TARGET.equals(ct.getType())){
            ChatTarget chatTarget = new ChatTarget();
            chatTarget.setType(Cts.SEND);
            chatTarget.setTargetUserId(getCurrentUser(session).getUserId());
            chatTarget.setContent(isInline(ct.getTargetUserId())?"在线":"离线");
            TextMessage msg = new TextMessage(GsonUtils.toJson(chatTarget));
            session.sendMessage(msg);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()) session.close();
        User user = getCurrentUser(session);
        inlineUserIds.remove(user.getUserId());
        sessions.remove(session);
        System.out.println("session " + user.getUsername() + " 异常，已关闭");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        User user = getCurrentUser(session);
        inlineUserIds.remove(user.getUserId());
        sessions.remove(session);
        System.out.println("session " + user.getUsername() + " 正常关闭");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 用户是否在线
     */
    private boolean isInline(Long userId){
        return inlineUserIds.contains(userId);
    }

    /**
     * 当前用户
     * @param session
     * @return
     */
    private User getCurrentUser(WebSocketSession session){
        HttpSession httpSession = (HttpSession)session.getAttributes().get("session");
        User user = (User)httpSession.getAttribute("user");
        return user;
    }
}
