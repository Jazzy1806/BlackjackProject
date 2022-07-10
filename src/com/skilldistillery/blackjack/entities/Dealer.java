package com.skilldistillery.blackjack.entities;

public class Dealer extends Player implements PlayerActions{
	
	public Dealer(String name) {
		super(name);
		super.hand = new BlackjackHand();
	}

	public void dealCards(Deck deck, Hand hand, int numberOfCards) {
		deck.dealCards(hand, numberOfCards);
	}

	public void hit(Deck deck) {
		hand.addCard(deck.dealCard());
	}

	public void stay() {
		System.out.println("Dealer has opted to stay.\n");
	}
	
	public String getName() {
		return name;
	}
	
	public Hand getHand() {
		return hand;
	}
}