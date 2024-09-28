package com.w4n4.dartViewer.webSockets;

import com.w4n4.dartViewer.model.User;
import com.w4n4.dartViewer.service.JwtService;
import com.w4n4.dartViewer.service.UserService;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class JwtHandshakeInterceptor implements HandshakeInterceptor {

    private final JwtService jwtService;
    private final UserService userService;

    public JwtHandshakeInterceptor(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        URI uri = request.getURI();
        String query = uri.getQuery(); // Pobiera całą część zapytania z URL

        // Szukamy tokenu w parametrach URL
        if (query != null && query.contains("token=")) {
            String token = query.split("token=")[1];



            if (jwtService.isValid(token, userService.findUserByUsername(jwtService.extractUsername(token)))) {
                attributes.put("token", token);
                return true;
            }
            return false;


        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception)
    {
        URI uri = request.getURI();
        String query = uri.getQuery();

        if (query != null && query.contains("token=")) {
            String token = query.split("token=")[1];


        }
    }
}
