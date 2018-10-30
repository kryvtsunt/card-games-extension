package edu.northeastern.ccs.cs5500;

import edu.northeastern.ccs.cs5500.problem1.Game;

public interface RoundGame extends Game{
    public void playRound();
    public boolean gameOver();
}
