package com.skilldistillery.blackjack.entities;

public enum Rank {
	TWO("2", 2), THREE("3", 3), FOUR("4", 4), FIVE("5", 5), SIX("6", 6), SEVEN("7", 7), EIGHT("8", 8), 
	NINE("9", 9), TEN("10", 10), JACK("J", 10), QUEEN("Q", 10), KING("K", 10), ACE("A", 11);
	
	private String cardFace;
	private int rank;
	
	Rank(String cardFace, int rank) {
		this.cardFace = cardFace;
		this.rank = rank;
	}
	
	public int getValue() {
		return rank;
	}
	
	@Override
	public String toString() {
		return cardFace;
	}
}