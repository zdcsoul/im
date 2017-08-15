package com.visizen.im.config;

import com.google.gson.Gson;
import com.visizen.im.user.entity.User;
import com.visizen.im.utils.ChatTarget;
import com.visizen.im.utils.CommonUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Administrator on 2017/8/8.
 */
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private static final Map<Long,WebSocketSession> map = Collections.synchronizedMap(new HashMap<>());

    private static final Gson gson = new Gson();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        User user = getCurrentUser(session);
        map.put(user.getUserId(),session);
        System.out.println("client " + user.getUsername() + " 已连接");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ChatTarget ct = gson.fromJson(message.getPayload(),ChatTarget.class);
        ct.setSendTime(CommonUtils.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
        if(Cts.TARGET.equals(ct.getType())){
            ct.setContent(isInline(ct.getTargetUser().getUserId())?"在线":"离线");
            TextMessage msg = new TextMessage(gson.toJson(ct));
            session.sendMessage(msg);
        }else if(Cts.SEND.equals(ct.getType())){
            WebSocketSession targetSession = map.get(ct.getTargetUser().getUserId());
            targetSession.sendMessage(new TextMessage(gson.toJson(ct)));
            session.sendMessage(new TextMessage(gson.toJson(ct)));
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()) session.close();
        User user = getCurrentUser(session);
        map.remove(user.getUserId());
        System.out.println("client " + user.getUsername() + " 异常，已关闭");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        User user = getCurrentUser(session);
        map.remove(user.getUserId());
        System.out.println("client " + user.getUsername() + " 正常关闭");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 用户是否在线
     */
    private boolean isInline(Long userId){
        return map.containsKey(userId);
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

    /**
     * 获取我的在线好友Id
     * @return
     */
    public static List<Long> getMyFrendCount(List<Long> frendIds){
        Set<Long> onlineLong = map.keySet();
        List<Long> ids = new ArrayList<>();
        for(Long frendId : frendIds){
            if(onlineLong.contains(frendId)) ids.add(frendId);
        }
        return ids;
    }
}
