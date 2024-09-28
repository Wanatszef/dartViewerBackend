package com.w4n4.dartViewer.webSockets;

import com.w4n4.dartViewer.config.WebSocketConfig;
import com.w4n4.dartViewer.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketSessionManager
{
    private final Map<User, WebSocketSession> userSessions = new ConcurrentHashMap<>();

    public void addSession(User user, WebSocketSession session)
    {
        userSessions.put(user, session);
    }

    public void removeSession(User user)
    {
        userSessions.remove(user);
    }

    public WebSocketSession getSession(User user)
    {
        return userSessions.get(user);
    }

    public boolean isUserConnected(User user)
    {
        return (user.getStatus().equals("Online") || userSessions.containsKey(user));
    }
}
