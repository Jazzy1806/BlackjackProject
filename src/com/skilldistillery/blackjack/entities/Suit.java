package com.skilldistillery.blackjack.entities;

public enum Suit {
	HEARTS("\u2764 (Hearts)"), SPADES("\u2660 (Spades)"), CLUBS("\u2663 (Clubs)"), DIAMONDS("\u2666 (Diamonds)");
	
	private String name;
	
	Suit(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}