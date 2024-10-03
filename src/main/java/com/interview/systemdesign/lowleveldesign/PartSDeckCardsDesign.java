package com.interview.systemdesign.lowleveldesign;

import java.util.*;

/**
 * Date 05/08/2020
 * @author Mukesh Kumar Gupta
 *
 * Reference: https://www.youtube.com/watch?v=lDa8I7iA5FA&list=PLIA-9QRQ0RqHcTCuIusS1G1LikGoD3WxM&index=7
 * Reference: https://www.geeksforgeeks.org/design-val-structuresclasses-objectsfor-generic-deck-cards/?ref=rp
 * Difficulty: Hard
 * Company: Google, Amazon, Facebook
 * Status: Not Done
 */

public class PartSDeckCardsDesign {
    
    enum Suit {
        Diamond, Club, Heart, Spade
    }
    
    class Card {
        int faceValue;
        Suit suit;
        public Card(int faceValue, Suit suit) {
            this.faceValue = faceValue;
            this.suit = suit;
            
        }
    }
    
    class Deck {
        List<Card> cardDeck;
        public Deck() {
            this.cardDeck = new ArrayList<Card>();
            for (int value = 1; value <= 13; value++) {
                for (Suit suit: Suit.values()) {
                    cardDeck.add(new Card(value, suit));
                }
            }
        }
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "ABC");
        
    }
}
