package com.w4n4.dartViewer.webSockets;

import com.w4n4.dartViewer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@Component
public class GameInviteHandler extends TextWebSocketHandler
{
    private final WebSocketSessionRegistry sessionRegistry;

    private final UserService userService;

    public GameInviteHandler(WebSocketSessionRegistry sessionRegistry, UserService userService) {
        this.sessionRegistry = sessionRegistry;
        this.userService = userService;
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception
    {
        super.afterConnectionEstablished(session);

        System.out.println(session.getId() + "Connected");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null) {
            sessionRegistry.registerSession(userService.findUserByUsername(authentication.getName()).getId(), session);
        }
        else{
            System.out.println("Cannot register Session");
        }


    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception
    {
        super.afterConnectionClosed(session, status);
        System.out.println("Session : " + session.getId() + " ended, User"+sessionRegistry.getUserIdBySession(session) +" logged out");
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception
    {
        super.handleMessage(session, message);

        String[] parts = message.toString().split(":");
        Integer targetUserId = Integer.parseInt(parts[0]);
        String gameDetails = parts[1];

        WebSocketSession targetSession = sessionRegistry.getSession(targetUserId);

        if (targetSession != null && targetSession.isOpen()) {
            targetSession.sendMessage(new TextMessage("GameInvite:" + gameDetails));
        }


    }
}
