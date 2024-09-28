package com.w4n4.dartViewer.config;

import com.w4n4.dartViewer.service.JwtService;
import com.w4n4.dartViewer.service.UserService;
import com.w4n4.dartViewer.webSockets.GameInvitationHandler;
import com.w4n4.dartViewer.webSockets.JwtHandshakeInterceptor;
import com.w4n4.dartViewer.webSockets.WebSocketHandler;
import com.w4n4.dartViewer.webSockets.WebSocketSessionManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer
{
    private final JwtService jwtService;
    private final UserService userService;
    private final WebSocketSessionManager sessionManager;
    private final GameInvitationHandler gameInvitationHandler;

    public WebSocketConfig(JwtService jwtService, UserService userService, WebSocketSessionManager sessionManager, GameInvitationHandler gameInvitationHandler) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.sessionManager = sessionManager;
        this.gameInvitationHandler = gameInvitationHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry)
    {
        registry.addHandler(new WebSocketHandler(jwtService,userService,sessionManager, gameInvitationHandler), "/invitationSocket-endpoint")
                .setAllowedOrigins("*")
                .addInterceptors(new JwtHandshakeInterceptor(jwtService,userService));
    }
}
