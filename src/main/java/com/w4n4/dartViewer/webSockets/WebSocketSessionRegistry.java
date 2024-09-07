package com.w4n4.dartViewer.webSockets;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class WebSocketSessionRegistry
{
    private final ConcurrentMap<Integer, WebSocketSession> sessions = new ConcurrentHashMap<>();

    public void registerSession(Integer userId, WebSocketSession session)
    {
        sessions.put(userId,session);
    }

    public WebSocketSession getSession(Integer userId)
    {
        return sessions.get(userId);
    }

    public void removeSession(Integer userId) {

        if (sessions.get(userId) != null)
        {
            sessions.remove(userId);
        }
    }

    public int getUserIdBySession(WebSocketSession session)
    {
        for(Map.Entry<Integer, WebSocketSession> entry: sessions.entrySet()) {


            if(entry.getValue() == session) {
                    return entry.getKey();
            }
            else
            {
            return -1;
            }
        }
        return -1;
    }

}

