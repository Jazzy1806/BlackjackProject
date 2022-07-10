package com.skilldistillery.blackjack.app;

import java.util.*;

import com.skilldistillery.blackjack.entities.BlackjackHand;
import com.skilldistillery.blackjack.entities.Deck;
import com.skilldistillery.blackjack.entities.Hand;
import com.skilldistillery.blackjack.entities.Dealer;
import com.skilldistillery.blackjack.entities.Player;

public class BlackjackApp {

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		BlackjackApp app = new BlackjackApp();
		app.gameOn();
	}

	public void gameOn() {
		boolean playAgain = true;
		String decision = "";
		boolean winner = false;

		do {
			Deck shoe = new Deck();
			shoe.shuffle();
			Dealer dealer = new Dealer("Dealer");
			Player player1 = new Player("Don Q");
			Player player2 = new Player("Mertle");

			Map<Player, Hand> players = new HashMap<>();
			players.put(player1, player1.getHand());
			players.put(player2, player2.getHand());
			
			dealer.dealCards(shoe, dealer.getHand(), 2);
			BlackjackHand dealerHand = (BlackjackHand) dealer.getHand();
			dealerHand.displayDealer();
			int dealerValue = dealerHand.getHandValue();

			Set<Player> playerSet = players.keySet();
			for (Player player : playerSet) {
				BlackjackHand pHand = (BlackjackHand) player.getHand();

				dealer.dealCards(shoe, pHand, 2);
			
				while (shoe.deckSize() > 0) {
					int handValue = pHand.getHandValue();
					if (pHand.isBust()) {
						System.out.println("\n" + player.getName() + " hand is a bust: " + handValue);
						pHand.displayPlayer();
						break;
					}
					else if (pHand.isBlackjack()) {
						System.out.println("\n" + player.getName() + " has a blackjack!");
						pHand.displayPlayer();
						break;
					}
					System.out.println("\n" + player.getName() + " hand: " + handValue);
					pHand.displayPlayer();
						System.out.println("\nWhat do you want to do?" +
											"\nHit: type \"hit\"" +
											"\nStay: type \"stay\"");
						decision = scan.nextLine().toLowerCase();
						if (!decision.equals("hit") && !decision.equals("stay")) {		
							System.out.println("That's not an option.");
						} 
					switch (decision) {
					case "hit": 
						pHand.addCard(shoe.dealCard());
						continue;
					case "stay": player.stay(); break;
					default: continue;
					} break; 
				}
			}
			dealerValue = dealerHand.getHandValue();
			while (dealerValue < 17) {
				System.out.println("\nDealer Hand: " + dealerValue);
				dealerHand.displayPlayer();
				if (dealerValue < 17) {
					System.out.println("Dealer wants a hit.");
					dealer.dealCards(shoe, dealerHand, 1);
					dealerValue = dealer.getHand().getHandValue();
				}
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
				BlackjackHand pHand = (BlackjackHand) player.getHand();
				int playerValue = pHand.getHandValue();
				System.out.println("\n" + player.getName() + " hand value is " + playerValue);
				pHand.displayPlayer();
				
				if (dealerHand.isBlackjack() && pHand.isBlackjack()) {
					System.out.println("It's a tie! " + player.getName() + " and the dealer both got blackjacks!"); 			//Add more info for multiple players
				}
				else if (!dealerHand.isBust() && dealerValue == playerValue) {
					System.out.println("It's a tie! " + player.getName() + " and the dealer both got the same value!"); 			//Add more info for multiple players
				}
				else if ((dealerValue < playerValue || dealerHand.isBust()) && !pHand.isBust()) {
					if (pHand.isBlackjack()) {
						System.out.println(player.getName() + " got a blackjack!");
					} else {
					System.out.println(player.getName() + " won!");
					}
				}
				else if (pHand.isBust() && dealerHand.isBust()) {
					System.out.println(player.getName() + " and the dealer both busted.");
				}
				else if ((pHand.isBust() || playerValue < dealerValue) && !dealerHand.isBust()) {
					System.out.println(player.getName() + " lost!");
				}
		}
		if (shoe.deckSize() == 0 || winner == false) {
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