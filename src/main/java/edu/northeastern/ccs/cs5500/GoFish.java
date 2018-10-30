package edu.northeastern.ccs.cs5500;

import edu.northeastern.ccs.cs5500.problem1.*;

import java.util.*;

public class GoFish extends SimpleGame implements Game, RoundGame {

    /**
     * List of Hands (all game players).
     */
    private NewHand player1;
    private NewHand player2;
    /**
     * Deck of Card for the Game.
     */
    private Deck deck;

    /**
     * Constructor.
     */
    public GoFish() {
        this.player1 = new StandardNewHand();
        this.player2 = new StandardNewHand();
        this.createDeck("STANDARD");
    }

    @Override
    public void createDeck(String str) throws IllegalArgumentException {
        if (str == null) {
            return;
        }
        DeckFactory deckFactory = (DeckFactory) FactoryProducer.getFactory(FactoryType.DECK);
        for (DeckType type : DeckType.values()) {
            if (str.equalsIgnoreCase(type.toString())) {
                this.deck = deckFactory.getDeck(type);
                return;
            }
        }
        throw new IllegalArgumentException("Invalid deck type");
    }

    @Override
    public void deal() throws IllegalArgumentException {
        if (this.deck == null) {
            throw new IllegalArgumentException("NULL argument");
        }
        acceptCard(player1);
        acceptCard(player2);
        Random random = new Random();
        int randInt = random.nextInt(this.deck.officialSize() - 2);
        try {
            this.deck.cut(randInt);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 6; i++) {
            acceptCard(player1);
            acceptCard(player2);
        }
    }


    @Override
    public void playRound() {
        while (true) {
            if (!fishing(player1, player2)) break;
        }
        while (true) {
            if (!fishing(player2, player1)) break;
        }
    }

    @Override
    public boolean gameOver() {
        return this.deck.emptyDeck() && this.emptyHands();
    }


    private void acceptCard(NewHand player) {
        if (!this.deck.emptyDeck()) {
            try {
                player.accept(this.deck.pullCard());
            } catch (IllegalAccessException e) {
                return;
            }
        }
    }


    private boolean fishing(NewHand player1, NewHand player2) {
        Rank rank = this.rankToFish(player1);
        if (player2.occurrencesInHand(rank) > 0) {
            Card card = player2.getCard(rank);
            player1.accept(card);
            this.removeCards(player1, rank);
            return true;
        }
        if (!deck.emptyDeck()) {
            try {
                player1.accept(this.deck.pullCard());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private Rank rankToFish(NewHand player) {
        Map<StandardRank, Integer> map = new HashMap<>();
        List<Card> cards = player.showCards();
        for (Card card : cards) {
            StandardRank rank = (StandardRank) card.getRank();
            if (map.containsKey(rank)) {
                int value = map.get(rank);
                value += 1;
                map.put(rank, value);
            } else {
                map.put(rank, 1);
            }
        }
        int max = -1;
        StandardRank res = null;
        for (Map.Entry<StandardRank, Integer> val : map.entrySet()) {
            if (max < val.getValue()) {
                res = val.getKey();
                max = val.getValue();
            }
        }
        return res;

    }

    private boolean emptyHands() {
        return (player1.showCards().size() == 0) && (player2.showCards().size() == 0);
    }

    private void removeCards(NewHand hand, Rank rank) {
        if (hand.occurrencesInHand(rank) >= 4) {
            while (hand.occurrencesInHand(rank) > 0) {
                hand.getCard(rank);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.deck != null) {
            sb.append("Deck:").append(this.deck.toString()).append("\n");
        }

        sb.append("Player 1: ").append(this.player1.showCards()).append("\n");
        sb.append("Player 2: ").append(this.player2.showCards()).append("\n");


        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }


}
