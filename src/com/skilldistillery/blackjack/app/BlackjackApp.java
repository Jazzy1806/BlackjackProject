package com.skilldistillery.blackjack.app;

import java.util.*;

import com.skilldistillery.blackjack.entities.BlackjackHand;
import com.skilldistillery.blackjack.entities.Card;
import com.skilldistillery.blackjack.entities.Deck;
import com.skilldistillery.blackjack.entities.Dealer;
import com.skilldistillery.blackjack.entities.Player;
import com.skilldistillery.blackjack.entities.Rank;
import com.skilldistillery.blackjack.entities.Suit;

public class BlackjackApp {

	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		BlackjackApp app = new BlackjackApp();
		app.gameOn();
	}

	public void gameOn() {
		boolean playAgain = true;
		String decision = "";

		do {
			Deck shoe = new Deck();
			shoe.shuffle();
			Dealer dealer = new Dealer("Dealer");
			Player player1 = new Player("Don Q");
			Player player2 = new Player("Mertle");

			Map<Player, BlackjackHand> players = new HashMap<>();
			players.put(player1, player1.getHand());
			players.put(player2, player2.getHand());
			
			dealer.dealCards(shoe, dealer.getHand(), 2);
//			Comment out line 33 and uncomment this section to test softValue functionality
//			Card ace1 = new Card(Suit.valueOf("HEARTS"), Rank.valueOf("ACE"));
//			Card ace2 = new Card(Suit.valueOf("DIAMONDS"), Rank.valueOf("ACE"));
//			Card ace3 = new Card(Suit.valueOf("CLUBS"), Rank.valueOf("ACE"));
//			Card ace4 = new Card(Suit.valueOf("SPADES"), Rank.valueOf("ACE"));
//			dealer.getHand().addCard(ace1);
//			dealer.getHand().addCard(ace2);
//			dealer.getHand().addCard(ace3);
//			dealer.getHand().addCard(ace4);
//			shoe.dealCard(ace1);
//			shoe.dealCard(ace2);
//			shoe.dealCard(ace3);
//			shoe.dealCard(ace4);
			BlackjackHand dealerHand = dealer.getHand();
			BlackjackHand pHand;
			dealerHand.displayDealer();
			int dealerValue = dealerHand.bestValue();

			Set<Player> playerSet = players.keySet();
			for (Player player : playerSet) {
				pHand = player.getHand();
				dealer.dealCards(shoe, pHand, 2);
			
				while (shoe.deckSize() > 0) {
					int handValue = pHand.bestValue();
					if (pHand.isBust()) {
						System.out.println("\n" + player.getName() + " hand is a bust: " + handValue);
						pHand.displayPlayer();
						break;
					}
					else if (pHand.isBlackjack() || handValue == 21) {
						System.out.println("\n" + player.getName() + " hand value is 21!");
						pHand.displayPlayer();
						break;
					}
					else if (pHand.isSoft()) {
						System.out.println("\n" + player.getName() + " has a soft hand. This hand's value can be any of the following: ");
						pHand.showValueAlternates();
					}
					System.out.println("\n" + player.getName() + "'s hand - best value: " + handValue);
					pHand.displayPlayer();
					System.out.println("\nWhat do you want to do?" +
										"\nHit: type \"hit\"" +
										"\nStay: type \"stay\"");
					decision = scan.nextLine().toLowerCase();
					if (!decision.equals("hit") && !decision.equals("stay")) {		
						System.out.println("That's not an option.");
					} 
					switch (decision) {
					case "hit": player.hit(shoe); continue;
					case "stay": player.stay(); break;
					default: continue;
					} break; 
				}
			}
			if (dealerHand.isSoft()) {
				System.out.println("\nDealer has a soft hand. This hand's value can be any of the following: ");
				dealerHand.showValueAlternates();
			}
			while (dealerValue < 17) {
				System.out.println("\nDealer Hand - best value: " + dealerValue);
				dealerHand.displayPlayer();
				System.out.println("Dealer wants a hit.");
				dealer.hit(shoe);
				dealerValue = dealer.getHand().bestValue();
			}
			System.out.println("\n\nFinal Results:");
			System.out.println("\nDealer hand value is " + dealerValue);
			dealerHand.displayPlayer();
			if (dealerHand.isBust()) {
				System.out.print("Dealer busted!\n");
			}
			else if (dealerHand.isBlackjack()) {
				System.out.print("Dealer has a blackjack!\n");
			}
			for (Player player : playerSet) {
				pHand = player.getHand();
				int playerValue = pHand.bestValue();
				System.out.println("\n" + player.getName() + " hand value is " + playerValue);
				pHand.displayPlayer();
				
				if (dealerHand.isBlackjack() && pHand.isBlackjack()) {
					System.out.println("It's a tie! " + player.getName() + " and the dealer both got blackjacks!");
				}
				else if (dealerHand.isBust() && pHand.isBust()) {
					System.out.println(player.getName() + " and the dealer both busted.");
				}
				else if (dealerHand.isBlackjack() || pHand.isBust() || (playerValue < dealerValue && 
						!dealerHand.isBust())) {
					System.out.println(player.getName() + " lost!");
				}
				else if (pHand.isBlackjack() || dealerHand.isBust() || dealerValue < playerValue) {
					if (pHand.isBlackjack()) {
						System.out.print(player.getName() + " got a blackjack! ");
					} 	System.out.println(player.getName() + " won!");
				}
				else if (dealerValue == playerValue) {
					System.out.println("It's a tie! " + player.getName() + " and the dealer both got equal hands.");
				}
		}
		if (shoe.deckSize() == 0 || playAgain) {
			System.out.println("\nThat's the end of this round. Do you want to play again? ");
			String response = scan.nextLine().toLowerCase();
			if (response.equals("n") || response.equals("no")) {
				playAgain = false;
			}
		}
		}while(playAgain);
		exit();
	}

	public void exit() {
	System.out.println("Thanks for playing!");
	scan.close();
	System.exit(0);
	}
}