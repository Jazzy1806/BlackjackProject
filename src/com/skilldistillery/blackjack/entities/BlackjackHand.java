package com.skilldistillery.blackjack.entities;

import java.util.*;

public class BlackjackHand extends Hand {

	public BlackjackHand() {
		super.hand = new ArrayList<>();
	}

	@Override
	public void displayDealer() {
		System.out.println("Dealer Hand: ");
		System.out.print("1st card blind, ");
		//Starting at 1 to leave first card "face down"
		for (int index = 1; index < hand.size(); index++) {		
			System.out.println(hand.get(index).toString());
		}
	}
	
	@Override
	public void displayPlayer() {
		System.out.println(hand.toString());
	}
	
	@Override
	public int getHandValue() {
		int handValue = 0;
		for (Card card : hand) {
			handValue += card.getValue();
		}
		return handValue;
	}
	
	public boolean isBlackjack() {
		if (getHandValue() == 21 && hand.size() == 2) {
			return true;
		}
		return false;
	}
	
	public boolean isBust() {
		if (getHandValue() > 21) {
			return true;
		}
		return false;
	}
}