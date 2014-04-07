package game.blackjack;
public class Player {

	private String playerName; // Name of the Player
	private int numberOfCards;
	private Cards[] cardsInHand = new Cards[10];

	/*
	 * Player Constructor for setting player name and emptying hand
	 */
	public Player(String aName) {
		// this is typically guest or dealer
		this.playerName = aName;
	}

	/*
	 * Empty Hands will cleanup before the next deal
	 */
	public void emptyHands() {

		for (int i = 0; i < 10; i++) {
			this.cardsInHand[i] = null;
		}

		this.numberOfCards = 0;
	}

	/*
	 * Get the next cards from stack and add it to player or dealers hand this
	 * also returns if the total in hand exceeds 21 so they can be busted
	 */
	public boolean addCards(Cards aCard) {
		if (this.numberOfCards == 10) {
			System.err.print("Too Many Cards in Hand, aborting. \n");
			System.exit(1);
		}

		this.cardsInHand[this.numberOfCards] = aCard;
		this.numberOfCards++;

		return (this.getSumOfCards() > 21);

	}

	/*
	 * Get total value of cards in hand, if there is more than 1 Ace then take
	 * away 10 from the value
	 */
	public int getSumOfCards() {
		int sumofCards = 0;
		int countAce = 0;

		for (int i = 0; i < this.numberOfCards; i++) {
			sumofCards += this.cardsInHand[i].getCardValue();
			countAce += this.cardsInHand[i].countAce();
		}

		while (countAce > 0 && sumofCards > 21) {
			sumofCards -= 10;
			countAce--;
		}

		return sumofCards;
	}

	/*
	 * Print the cards that are in dealers or players hand
	 */
	public void printCardsInHand(boolean showCard) {
		System.out.printf("%s's Cards \n", this.playerName);
		for (int i = 0; i < numberOfCards; i++) {
			if (i == 0 && !showCard) {
				// first time when we deal we have to hide one of dealers card
				System.out.printf("---->[%s] \n", "Hidden"); 
			} else {
				System.out.printf("---->%s \n", this.cardsInHand[i].toString());
			}
		}

	}

}
