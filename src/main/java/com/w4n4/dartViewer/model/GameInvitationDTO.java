package com.w4n4.dartViewer.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

public class GameInvitationDTO implements Serializable
{

    private long id;

    private int senderId;

    private long gameMatchId;


    public GameInvitationDTO(GameInvitation gameInvitation)
    {
        this.id = gameInvitation.getId();
        this.senderId = gameInvitation.getSender().getId();
        this.gameMatchId = gameInvitation.getGameMatch().getId();
    }

    @Override
    public String toString() {
        return "GameInvitationDTO{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", gameMatchId=" + gameMatchId +
                '}';
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGameMatchId() {
        return gameMatchId;
    }

    public void setGameMatchId(long gameMatchId) {
        this.gameMatchId = gameMatchId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }
}
