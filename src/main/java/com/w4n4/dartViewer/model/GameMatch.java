package com.w4n4.dartViewer.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Random;

@Entity
public class GameMatch
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player1_id")
    private User player1;

    @ManyToOne
    @JoinColumn(name = "player2_id")
    private User player2;

    private int targetPoints;
    private int totalLegs;

    private int wonLegsPlayer1;

    private int wonLegsPlayer2;

    private boolean isPlayer1Starting;

    private boolean isGameFinished;

    private String gameType;

    @OneToMany(mappedBy = "gameMatch", cascade = CascadeType.ALL)
    private List<Leg> legs;

    public GameMatch(User player1, User player2, int targetPoints, int totalLegs) {
        this.player1 = player1;
        this.player2 = player2;
        this.targetPoints = targetPoints;
        this.totalLegs = totalLegs;

        if(Math.round(Math.random()) == 0)
        {
            isPlayer1Starting = true;
        }
        else
        {
            isPlayer1Starting = false;
        }

        wonLegsPlayer1 = 0;
        wonLegsPlayer2 = 0;

        isGameFinished = false;
    }

    public GameMatch() {
    }

    public void startNewLeg()
    {
        int legNumber = legs.size()+1;
        Leg newLeg = new Leg(this,legNumber,targetPoints, isPlayer1Starting);
    }

    public boolean isMatchFinished()
    {
        return wonLegsPlayer1 > totalLegs /2 || wonLegsPlayer2 > totalLegs /2;
    }

    public User getPlayer1() {
        return player1;
    }

    public void setPlayer1(User player1) {
        this.player1 = player1;
    }

    public User getPlayer2() {
        return player2;
    }

    public void setPlayer2(User player2) {
        this.player2 = player2;
    }

    public int getTargetPoints() {
        return targetPoints;
    }

    public void setTargetPoints(int targetPoints) {
        this.targetPoints = targetPoints;
    }

    public int getTotalLegs() {
        return totalLegs;
    }

    public void setTotalLegs(int totalLegs) {
        this.totalLegs = totalLegs;
    }

    public int getWonLegsPlayer1() {
        return wonLegsPlayer1;
    }

    public void setWonLegsPlayer1(int wonLegsPlayer1) {
        this.wonLegsPlayer1 = wonLegsPlayer1;
    }

    public int getWonLegsPlayer2() {
        return wonLegsPlayer2;
    }

    public void setWonLegsPlayer2(int wonLegsPlayer2) {
        this.wonLegsPlayer2 = wonLegsPlayer2;
    }

    public boolean isPlayer1Starting() {
        return isPlayer1Starting;
    }

    public void setPlayer1Starting(boolean player1Starting) {
        isPlayer1Starting = player1Starting;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isGameFinished() {
        return isGameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        isGameFinished = gameFinished;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }
}
