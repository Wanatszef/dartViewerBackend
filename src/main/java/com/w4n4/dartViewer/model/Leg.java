package com.w4n4.dartViewer.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "leg")
public class Leg
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private GameMatch gameMatch;

    private int legNumber;
    private int pointsPlayer1;
    private int pointsPlayer2;
    private boolean isPlayer1Turn;

    @OneToMany(mappedBy = "leg", cascade = CascadeType.ALL)
    private List<GameEvent> events;

    public Leg(GameMatch gameMatch, int legNumber, int initialPoints, boolean startingPlayer1)
    {
        this.gameMatch = gameMatch;
        this.pointsPlayer1 = initialPoints;
        this.pointsPlayer2 = initialPoints;
        this.legNumber = legNumber;
        if(startingPlayer1)
        {
            isPlayer1Turn = true;
        }
        else
        {
            isPlayer1Turn = false;
        }
    }

    public boolean isLegFinished()
    {
        return pointsPlayer1 == 0 || pointsPlayer2 == 0;
    }

    public void switchTurn()
    {
        this.isPlayer1Turn = !this.isPlayer1Turn;
    }
}
