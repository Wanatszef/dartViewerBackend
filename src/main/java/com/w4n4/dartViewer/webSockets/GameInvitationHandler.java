package com.w4n4.dartViewer.webSockets;

import com.w4n4.dartViewer.model.GameMatch;
import com.w4n4.dartViewer.model.User;
import com.w4n4.dartViewer.service.GameService;
import com.w4n4.dartViewer.service.UserService;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Component
public class GameInvitationHandler
{
    private final WebSocketSessionManager sessionManager;
    private final UserService userService;
    private final GameService gameService;
    //todo stworzyć GameService

    public GameInvitationHandler(WebSocketSessionManager sessionManager, UserService userService, GameService gameService) {
        this.sessionManager = sessionManager;
        this.userService = userService;
        this.gameService = gameService;
    }

    public void handle(JSONObject object, WebSocketSession session) throws IOException {
        String command = object.getString("command");
        switch(command)
        {
            case "sendInvite":
                sendGameInvitation(object,session);
                break;

        }

    }

    public void sendGameInvitation(JSONObject object, WebSocketSession session) throws IOException {

        long gameId = object.getLong("GameID");
        if(gameId != -1) {
            GameMatch game =gameService.getGameMatchById(gameId);
            if(game != null)
            {
               if(game.getPlayer2().getId() != -1)
               {
                   return;
               }
               else
               {

                   // Tworzenie odpowiedzi w formacie JSON
                   JSONObject responseJson = new JSONObject();
                   responseJson.put("type","gameInvitation");
                   JSONObject data = new JSONObject();
                   data.put("from" ,game.getPlayer1().getUsername());
                   data.put("targetPoints", game.getTargetPoints());
                   data.put("totalLEgs", game.getTotalLegs());
                   responseJson.put("data", data);

                   // Wysyłanie wiadomości JSON jako TextMessage
                   session.sendMessage(new TextMessage(responseJson.toString()));
               }
            }
        }

    }

    public void acceptGameInvitation(JSONObject object, WebSocketSession session)
    {

    }

    public void rejectGameInvitation(JSONObject object, WebSocketSession session)
    {

    }
}
