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
	
	public int deckSize() {
		return deck.size();
	}
	
	public Card dealCard() {
		return deck.remove(0);
	}

	public boolean dealCard(Card card) {
		return deck.remove(card);
	}

	public void dealCards(BlackjackHand hand, int numberOfCards) {
		for (int i = 0; i < numberOfCards; i++) {
		hand.addCard(deck.remove(0));
		}
	}
	
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
	public void showDeck() {
		for (Card card : deck) {
			System.out.println(card);
		}
	}
}