package com.w4n4.dartViewer.webSockets;

import com.w4n4.dartViewer.model.User;
import com.w4n4.dartViewer.service.JwtService;
import com.w4n4.dartViewer.service.UserService;
import jakarta.websocket.Session;
import org.json.JSONObject;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketHandler extends TextWebSocketHandler
{

    private final JwtService jwtService;
    private final UserService userService;
    private final WebSocketSessionManager sessionManager;
    private final GameInvitationHandler gameInvitationHandler;

    public WebSocketHandler(JwtService jwtService, UserService userService, WebSocketSessionManager sessionManager, GameInvitationHandler gameInvitationHandler) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.sessionManager = sessionManager;
        this.gameInvitationHandler = gameInvitationHandler;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception
    {
        String payload = message.getPayload();

        JSONObject jsonMsg = new JSONObject(payload);
        String token = (String) session.getAttributes().get("token");
        String type = jsonMsg.getString("type");

        if(token != null)
        {
            User user = userService.findUserByUsername(jwtService.extractUsername(token));
            if(user.getId() != -1 || jwtService.isValid(token,user))
            {
                switch(type)
                {
                    case "gameInvitation":
                        System.out.println("AAAAAA");
                        int userID = jsonMsg.getInt("userId");
                        JSONObject data = jsonMsg.getJSONObject("data");
                        userID = (int) data.get("to");
                        if(userID != -1) {
                            WebSocketSession receiverSession = sessionManager.getSession(userService.findUserById(userID));
                            if (receiverSession != null) {
                                gameInvitationHandler.handle(jsonMsg.getJSONObject("data"), receiverSession);
                            }
                            else System.out.println("Cannot find userSession");
                        }
                        break;

                }
            }
            else System.out.println("user == null");
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
       String token = (String) session.getAttributes().get("token");

       if(token != null)
       {
        User user = userService.findUserByUsername(jwtService.extractUsername(token));
           if(user.getId() != -1)
           {
               sessionManager.addSession(user,session);
               user.setStatus("Online");
           }
           else System.out.println("user == null");
       }
       else
       {
           System.out.println("Token == null");
       }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception
    {
        String token = (String) session.getAttributes().get("token");

        if(token != null)
        {
            User user = userService.findUserByUsername(jwtService.extractUsername(token));
            if(user.getId() != -1)
            {
                sessionManager.removeSession(user);
                user.setStatus("Offline");
            }
            else System.out.println("user == null");
        }
        else
        {
            System.out.println("Token == null");
        }
    }

}
