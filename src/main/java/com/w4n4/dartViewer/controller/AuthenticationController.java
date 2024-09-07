package com.w4n4.dartViewer.controller;


import com.w4n4.dartViewer.model.AuthenticationResponse;
import com.w4n4.dartViewer.model.User;
import com.w4n4.dartViewer.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import static com.w4n4.dartViewer.config.FrontEndSettings.FRONT_URL;


@RestController
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody User request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody User request
    ) {
        System.out.println("login user" + request.getUsername() + request.getPassword());
        return ResponseEntity.ok(authService.authenticate(request));
    }


}