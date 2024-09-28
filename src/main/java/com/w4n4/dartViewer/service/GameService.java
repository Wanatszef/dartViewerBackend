package com.w4n4.dartViewer.service;

import com.w4n4.dartViewer.model.GameMatch;
import com.w4n4.dartViewer.repository.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService
{
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameMatch getGameMatchById(long id)
    {
        if(gameRepository.findById(id).isPresent())
        {
            return gameRepository.findById(id).get();
        }
        else
        {
            System.out.println("Cannot find gameMatch by Id");
            return null;
        }
    }

    public void save(GameMatch gameMatch)
    {
        gameRepository.save(gameMatch);
    }
}
