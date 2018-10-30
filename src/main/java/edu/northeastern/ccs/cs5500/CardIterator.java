package edu.northeastern.ccs.cs5500;

import edu.northeastern.ccs.cs5500.problem1.Card;
import edu.northeastern.ccs.cs5500.problem1.Hand;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CardIterator implements Iterator<Card> {

    public List<Card> cards;
    private int index;

    public CardIterator(Hand hand){
        this.cards = hand.showCards();
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < cards.size();
    }

    @Override
    public Card next() {
        if (hasNext()) {
            Card card = cards.get(index);
            index++;
            return card;
        }
        throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
