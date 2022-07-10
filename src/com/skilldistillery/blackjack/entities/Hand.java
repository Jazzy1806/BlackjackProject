package com.skilldistillery.blackjack.entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand {

	protected List<Card> hand;
	
	public Hand() {
		hand = new ArrayList<>();
	}
	
	public void addCard(Card card) {
		hand.add(card);
	}
	
	public void clear() {
		hand = new ArrayList<>();
	}
	
	public abstract int getHandValue();
	
	public abstract void displayDealer();
	
	public abstract void displayPlayer();
	
	public String toString() {
		String cardsInHand = "";
		for (Card card : hand) {
			cardsInHand += card + "\n";
		}
		return cardsInHand;
	}
}