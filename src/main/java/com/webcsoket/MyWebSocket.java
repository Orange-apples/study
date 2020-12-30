package com.webcsoket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/ws/{id}")
@Component
public class MyWebSocket {


    private static int onlineCount = 0;

    private static Map<String, MyWebSocket> clients = new ConcurrentHashMap<String, MyWebSocket>();

    private Session session;

    private String username;


    @OnOpen
    public void onOpen(@PathParam("id") String id, Session session) throws IOException {
        this.username = id;
        this.session = session;
        addOnlineCount();
        clients.put(username, this);
        onMessage("【" + this.username + "】进入了聊天室");
    }

    @OnClose
    public void onClose() throws IOException {
        clients.remove(username);
        onMessage("【" + this.username + "】退出了群聊");
        subOnlineCount();
    }


    @OnMessage
    public void onMessage(String message) throws IOException {
        sendMessageAll(message);
    }


    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }


    public void sendMessageTo(String message, String To) throws IOException {
        // session.getBasicRemote().sendText(message);
        //session.getAsyncRemote().sendText(message);
        for (MyWebSocket item : clients.values()) {
            if (item.username.equals(To))
                item.session.getAsyncRemote().sendText(message);
        }

    }


    public void sendMessageAll(String message) throws IOException {
        Set<String> strings = clients.keySet();
        HashMap<Object, Object> map = new HashMap<>();
        map.put("members", strings);
        map.put("msg", message);
        String msg = JSON.toJSONString(map);
        for (MyWebSocket item : clients.values()) {
            item.session.getAsyncRemote().sendText(msg);
        }
    }


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }


    public static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }


    public static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }


    public static synchronized Map<String, MyWebSocket> getClients() {
        return clients;
    }

}
