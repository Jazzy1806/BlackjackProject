package com.skilldistillery.blackjack.entities;

public class Dealer extends Player{
	
	public Dealer(String name) {
		super(name);
		super.hand = new BlackjackHand();
	}

	public void dealCards(Deck deck, Hand hand, int numberOfCards) {
		deck.dealCards(hand, numberOfCards);
	}

	@Override
	public void stay() {
		System.out.println("Dealer has opted to stay.\n");
	}
}