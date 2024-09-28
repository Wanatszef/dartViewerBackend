package com.w4n4.dartViewer.controller;

import com.w4n4.dartViewer.model.*;
import com.w4n4.dartViewer.service.GameService;
import com.w4n4.dartViewer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/games")
public class GameMatchController
{
    private final GameService gameService;
    private final UserService userService;

    @Autowired
    public GameMatchController(GameService gameService, UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
    }


    @PostMapping("/create")
    public ResponseEntity<GameInvitationDTO> addGameMatch(@RequestBody Map<String, Object> payload)
    {
        User player1 = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(player1 != null)
        {
            System.out.println(payload);
            Object targetPoints = payload.get("targetPoints");
            Object totalLegs = payload.get("totalLegs");
            Object gameType = payload.get("gameType");
            GameMatch gameMatch = new GameMatch(player1,null,Integer.parseInt(targetPoints.toString()),Integer.parseInt(totalLegs.toString()));
            gameMatch.setGameType(gameType.toString());
            gameService.save(gameMatch);
            GameInvitation gameInvitationTemplate = new GameInvitation();
            gameInvitationTemplate.setGameMatch(gameMatch);
            gameInvitationTemplate.setSender(player1);
            GameInvitationDTO gameInvitationDTOTemplate = new GameInvitationDTO(gameInvitationTemplate);
            return new ResponseEntity<>(gameInvitationDTOTemplate, HttpStatus.CREATED);
        }
        else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
