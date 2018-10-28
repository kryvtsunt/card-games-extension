package edu.northeastern.ccs.cs5500;

import edu.northeastern.ccs.cs5500.problem1.Card;
import edu.northeastern.ccs.cs5500.problem1.Hand;

import java.util.Iterator;
import java.util.List;

public class CardIterator implements Iterator<Card> {

    private List<Card> cards;
    private int index;

    public CardIterator(Hand hand){
        this.cards = hand.showCards();
    }

    @Override
    public boolean hasNext() {
        return index < cards.size();
    }

    @Override
    public Card next() {
        Card card = cards.get(index);
        index++;
        return card;
    }

    @Override
    public void remove() {
        cards.remove(index);
    }
}
