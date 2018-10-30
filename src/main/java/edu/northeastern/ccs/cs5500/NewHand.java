package edu.northeastern.ccs.cs5500;

import edu.northeastern.ccs.cs5500.problem1.Card;
import edu.northeastern.ccs.cs5500.problem1.Hand;
import edu.northeastern.ccs.cs5500.problem1.Rank;

public interface NewHand extends Hand {
    int occurrencesInHand(Card cardToFind);
    int occurrencesInHand(Rank rankToFind);
    Card getCard(Rank rankToFind);
}
