package edu.northeastern.ccs.cs5500;


import edu.northeastern.ccs.cs5500.problem1.Card;
import edu.northeastern.ccs.cs5500.problem1.Rank;
import edu.northeastern.ccs.cs5500.problem1.StandardHand;
import edu.northeastern.ccs.cs5500.problem1.StandardRank;

import java.util.List;

public class StandardNewHand extends StandardHand implements NewHand {

    @Override
    public Boolean hasCard(Card cardToFind) {
        CardIterator iterator = new CardIterator(this);
        while (iterator.hasNext()) {
            Card card = iterator.next();
            if (card.equals(cardToFind)) return true;
        }
        return false;
    }


    @Override
    public Card getCard(Rank rankToFind) {
        CardIterator iterator = new CardIterator(this);
        while (iterator.hasNext()) {
            Card card = iterator.next();
            if (card.getRank().equals(rankToFind)) {
                this.showCards().remove(card);
                return card;
            }
        }
        return null;
    }


    @Override
    public int occurrencesInHand(Card cardToFind) {
        CardIterator iterator = new CardIterator(this);
        int occurrences = 0;
        while (iterator.hasNext()) {
            Card card = iterator.next();
            if (card.equals(cardToFind)) occurrences++;
        }
        return occurrences;
    }

    @Override
    public int occurrencesInHand(Rank rankToFind) {
        CardIterator iterator = new CardIterator(this);
        int occurrences = 0;
        while (iterator.hasNext()) {
            Card card = iterator.next();
            if (card.getRank().equals(rankToFind)) occurrences++;
        }
        return occurrences;
    }

}
