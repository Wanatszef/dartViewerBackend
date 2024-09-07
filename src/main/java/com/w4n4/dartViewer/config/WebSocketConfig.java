package com.w4n4.dartViewer.config;

import com.w4n4.dartViewer.service.UserService;
import com.w4n4.dartViewer.webSockets.GameInviteHandler;
import com.w4n4.dartViewer.webSockets.WebSocketSessionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final GameInviteHandler gameInviteHandler;

    @Autowired
    public WebSocketConfig(GameInviteHandler gameInviteHandler) {
      this.gameInviteHandler = gameInviteHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry)
    {
        registry.addHandler(gameInviteHandler,"/game-invite").setAllowedOrigins("*");
    }
}
