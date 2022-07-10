package com.skilldistillery.blackjack.entities;

public interface PlayerActions {
	
	public void hit(Deck deck);

	public void stay();
	
	public String getName();
	
	public Hand getHand();
}
