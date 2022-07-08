package com.skilldistillery.blackjack.entities;

import java.util.*;

public class Deck {
	List<Card> deck;
	
	public Deck() {
		deck = new ArrayList<>();
		
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				deck.add(new Card(suit, rank));
			}
		}
	}
	
	public int checkDeckSize() {
		return deck.size();
	}
	
	public Card dealCard () {
		return deck.remove(0);
	}
	
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
}
