package game.blackjack;
import java.util.Scanner;

public class PlayBlackJack {

	public static void main(String[] args) {

		Scanner userInput = new Scanner(System.in);
		boolean dealerDone, guestDone;
		String answer, dealAgain = "Y", guestName = "Guest";
		int guestChip =100; //100 chips to start this game 

		Deck newDeal = new Deck(); // get a new deal
		Player dealer = new Player("Dealer"); // dealers hand

		System.out.print("Enter your Name :");
		guestName = userInput.next();
		Player guest = new Player(guestName); // guests hand

		while (dealAgain.compareToIgnoreCase("Y") == 0) { // keep dealing till
															// user quits
			// Initialize for new game
			guest.emptyHands();
			dealer.emptyHands();
			answer = "X";
			dealerDone = false;
			guestDone = false;
			/*
			 * Quit if there are not enough cards
			 */
			if (newDeal.avalilableCards() < 10) {
				System.err.print("Deck is too small to continue, please restart Game \n");
				System.exit(1);
			}

			System.out.println("Dealing cards now");
			guest.addCards(newDeal.getTopOfDeck()); // first card to guest
			dealer.addCards(newDeal.getTopOfDeck());// first card to dealer

			guest.addCards(newDeal.getTopOfDeck()); // second card to guest
			dealer.addCards(newDeal.getTopOfDeck()); // second card to dealer

			guest.printCardsInHand(true); // show both cards with out hiding
											// because it is guest
			System.out.printf("Total for " + guestName + " : %d\n\n",
					guest.getSumOfCards());
			dealer.printCardsInHand(false); // show only the first card and hide
											// the next for dealer
			System.out.printf("Total for Dealer : [ %s ]\n\n", "Hidden");

			/*
			 * In the first draw if either one reached their goal then proceed
			 * to find who won
			 */
			if (guest.getSumOfCards() >= 21) {
				guestDone = true;
			}

			if (dealer.getSumOfCards() >= 21) {
				dealerDone = true;
			}

			while (!guestDone) {
				System.out.print("Enter H for <Hit> S for <Stand> :");
				answer = userInput.next();

				if (answer.compareToIgnoreCase("H") == 0) { // if the player
															// hits
					System.out.println("\n" + guestName + " Hits");
					guestDone = guest.addCards(newDeal.getTopOfDeck());

					// show cards in hand for guest
					guest.printCardsInHand(true);
					System.out.printf("Total for " + guestName + " : %d\n\n", guest.getSumOfCards());
					// show cards in hand for dealer with out hiding
					dealer.printCardsInHand(true);
					System.out.printf("Total for Dealer : %d\n\n", dealer.getSumOfCards());
					// if guest gets 21 or more
					if (guest.getSumOfCards() >= 21) {
						guestDone = true;
					}
				} else if (answer.compareToIgnoreCase("S") == 0) { // guest
																	// wants to
																	// stand
					System.out.println("\n" + guestName + " Stands\n");
					guestDone = true;
					// show latest cards in hand for guest
					guest.printCardsInHand(true);
					System.out.printf("Total for " + guestName + " : %d\n\n", guest.getSumOfCards());
					// show latest cards in hand for dealer
					dealer.printCardsInHand(true);
					System.out.printf("Total for Dealer : %d\n\n", dealer.getSumOfCards());
				} // end of if guest selection
			}// end of while for guest done

			if (answer.compareToIgnoreCase("S") == 0) {
				while (!dealerDone) {
					if (dealer.getSumOfCards() < 17) {
						System.out.println("Dealer hits");
						dealerDone = dealer.addCards(newDeal.getTopOfDeck());

						guest.printCardsInHand(true);
						System.out.printf("Total for " + guestName  + " : %d\n\n", guest.getSumOfCards());

						dealer.printCardsInHand(true);
						System.out.printf("Total for Dealer : %d\n\n", dealer.getSumOfCards());
					} else { // dealer >= 17
						dealerDone = true;
					} // end of if for sum of cards
				}// end of while for dealer done
			} // end of if for Stand

			// now find out who won by printing total first
			System.out.println("Game Ends, Final Total \n");
			guest.printCardsInHand(true);
			System.out.printf("Total for " + guestName + " : %d\n\n", guest.getSumOfCards());

			dealer.printCardsInHand(true);
			System.out.printf("Total for Dealer : %d\n\n", dealer.getSumOfCards());

			int dealerTotal = dealer.getSumOfCards();
			int guestTotal = guest.getSumOfCards();
			/*
			 * 1. dealer > 21/ guest <=21 , guest wins 
			 * 2. guest <=21 /dealer < guest, guest wins 
			 * 3. guest = dealer, tie 
			 * 4. else dealer wins
			 */
			if ((guestTotal <= 21 && dealerTotal > 21)
					|| (guestTotal <= 21 && dealerTotal < guestTotal)) {
				guestChip +=1; 
				System.out.printf(guestName + " wins!, %s has $%d\n",guestName,guestChip);
			} else if (dealerTotal == guestTotal) {
				System.out.printf("Tie!, %s has $%d\n",guestName,guestChip);
			} else {
				guestChip-=1;
				System.out.printf("Dealer wins!, %s has $%d\n",guestName,guestChip);
			}// end of if for who won

			System.out.print("Continue Y/N ? :");
			dealAgain = userInput.next();

		}// while ends

		userInput.close(); // close the input stream

	} // end of main

}
