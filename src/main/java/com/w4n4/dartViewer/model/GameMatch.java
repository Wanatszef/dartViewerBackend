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

    @JoinColumn(name = "player2_id")
    private User player2;

    private int targetPoints;
    private int totalLegs;

    private int wonLegsPlayer1;

    private int wonLegsPlayer2;

    private boolean isPlayer1Starting;

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
}
