package edu.northeastern.ccs.cs5500;

import edu.northeastern.ccs.cs5500.problem1.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class HandTest {
    private Hand hand1;
    private Hand hand2;
    private Hand hand3;

    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;
    private Card card5;

    private Deck deck;


    /**
     * test hands construction
     */
    @Before
    public void testConstructor() {

        assertNull(hand1);
        assertNull(hand2);
        assertNull(hand3);

        hand1 = new StandardNewHand();
        hand2 = new StandardNewHand();
        hand3 = new StandardNewHand();

        assertNotNull(hand1);
        assertNotNull(hand2);
        assertNotNull(hand3);


        assertNull(card1);
        assertNull(card2);
        assertNull(card3);
        assertNull(card4);
        assertNull(card5);

        card1 = new StandardCard(StandardSuit.DIAMONDS, StandardRank.KING);
        card2 = new StandardCard(StandardSuit.DIAMONDS, StandardRank.TEN);
        card3 = new StandardCard(StandardSuit.HEARTS, StandardRank.KING);
        card4 = new StandardCard(StandardSuit.SPADES, StandardRank.THREE);
        card5 = new StandardCard(StandardSuit.CLUBS, StandardRank.ACE);

        assertNotNull(card1);
        assertNotNull(card2);
        assertNotNull(card3);
        assertNotNull(card4);
        assertNotNull(card5);

        assertNull(deck);
        deck = new StandardDeck();
        assertNotNull(deck);


    }

    /**
     * tests new functionality
     */
    @Test
    public void testIterator() {
        NewHand hand = new StandardNewHand();
        hand.accept(card1);
        hand.accept(card2);
        assertTrue(hand.hasCard(card1));
        assertFalse(hand.hasCard(card4));
        assertEquals(1, hand.occurrencesInHand(card1));
        assertEquals(1, hand.occurrencesInHand(StandardRank.KING));
        assertEquals(0, hand.occurrencesInHand(StandardRank.JACK));

    }

    @Test
    public void testRemove() {
        NewHand hand = new StandardNewHand();
        hand.accept(card1);
        hand.accept(card2);
        hand.accept(card3);
        hand.accept(card4);
        hand.accept(card5);
        assertEquals("[K♦, 10♦, K♥, 3♠, A♣]", hand.toString());
        hand.getCard(card1.getRank());
        assertEquals("[10♦, K♥, 3♠, A♣]", hand.toString());
    }

    @Test
    public void test() {
        RoundGame game = new GoFish();
        game.deal();
        while(!game.gameOver()){
            game.playRound();
        }
        assertEquals("Deck:[]\n" +
                "Player 1: []\n" +
                "Player 2: []", game.toString());

    }
}
