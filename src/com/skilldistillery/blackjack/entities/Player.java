package com.skilldistillery.blackjack.entities;

public class Player implements PlayerActions{
	protected Hand hand;
	protected String name;

	
	public Player(String name) {
		hand = new BlackjackHand();
		this.name = name;
	}

	public void hit(Deck deck) {
		hand.addCard(deck.dealCard());
	}

	public void stay() {
		System.out.println("Player has opted to stay.\n");
	}
	
	public String getName() {
		return name;
	}
	
	public Hand getHand() {
		return hand;
	}
}