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
		for (int index = 1; index < hand.size(); index++) {		
			System.out.println(hand.get(index).toString());
		}
	}
	
	@Override
	public void displayPlayer() {
		for (int index = 0; index < hand.size(); index++) {
			if (index == hand.size() - 1) {
				System.out.println(hand.get(index).toString());
			} else {
			System.out.print(hand.get(index).toString() + ", ");
			}
		}
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
		if (getHandValue() > 21 && bestValue() > 21) {
			return true;
		}
		return false;
	}
	
	public boolean isSoft() {
		for (Card card : hand) {
			if (card.getValue() == 11) {
				return true;
			}
		}
		return false;
	}
	
	public int numberAces() {
		int aces = 0;
		for (Card card : hand) {
			if (card.getValue() == 11) {
				aces ++;
			}
		} 
		return aces;
	}
	
	public int[] softValue() {
		int[] softValue = new int[5];
		//intentionally allowing fall-through
		switch (numberAces()) {
		case 4: softValue[4] = getHandValue() - 40;
		case 3: softValue[3] = getHandValue() - 30;
		case 2: softValue[2] = getHandValue() - 20;
		case 1: softValue[1] = getHandValue() - 10;
		case 0: softValue[0] = getHandValue();
		}
		return softValue;
	}
	
	public void showValueAlternates() {
		for (int value : softValue()) {
			if (value != 0) {
				System.out.println("Value: " + value);		
			}
		}
	}
	
	public int bestValue() {
		int bestValue = 0;
		int[] values = softValue();
		if (numberAces() > 0) {
			for (int index = 0; index < values.length; index++ )
				if (values[index] > bestValue && values[index] <= 21) {
					bestValue = values[index];
				}
				else if (values[index] == 0 && values[index - 1] > 21) {
					bestValue = values[index - 1];
				}
				else if (index + 1 == values.length && values[index] > 21) {
					bestValue = values[index];
				}
			}
		if (bestValue == 0){ 
			bestValue = values[0];
		}
		 return bestValue;
	}
}