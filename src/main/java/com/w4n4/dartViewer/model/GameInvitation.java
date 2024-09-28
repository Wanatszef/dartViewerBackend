package com.w4n4.dartViewer.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "game_invitation")
public class GameInvitation
{
    @Id
    @Column(name = "invite_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private User sender;


    private User receiver;


    @Column(name = "invitationDate")
    private LocalDateTime invitationDate;

    @ManyToOne
    @JoinColumn(name = "gameMatch")
    private GameMatch gameMatch;


    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public GameMatch getGameMatch() {
        return gameMatch;
    }

    public void setGameMatch(GameMatch gameMatch) {
        this.gameMatch = gameMatch;
    }

    public LocalDateTime getInvitationDate() {
        return invitationDate;
    }

    public void setInvitationDate(LocalDateTime invitationDate) {
        this.invitationDate = invitationDate;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GameInvitation() {
    }

    public GameInvitation( GameMatch gameMatch, LocalDateTime invitationDate, User receiver, User sender) {
        this.gameMatch = gameMatch;
        this.invitationDate = invitationDate;
        this.receiver = receiver;
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "GameInvitation{" +
                "id=" + id +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", invitationDate=" + invitationDate +
                ", gameMatch=" + gameMatch +
                '}';
    }
}
