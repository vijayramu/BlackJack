package game.blackjack;
public class Cards {

	private Suit suit; // suit of cards
	private Number cardNumber; // card number 1 through 13

	/*
	 * Constructor for a suit and card number
	 */
	public Cards(Suit aSuit, Number acardNumber) {
		this.suit = aSuit;
		this.cardNumber = acardNumber;
	}

	/*
	 * Overloading to string function to return card number suit example: Ace of
	 * Spades
	 */
	public String toString() {
		return this.cardNumber.toString() + " of " + this.suit.toString();
	}

	/*
	 * Counting Ace so it can be used to change to value of 1 if there are more
	 * than 1 Ace
	 */
	public int countAce() {
		int numberOfAce = 0;
		if (this.cardNumber.toString() == "Ace") {
			numberOfAce++;
		}
		return numberOfAce;
	}

	/*
	 * Get the total card value
	 */
	public int getCardValue() {
		int cardValue = 0;

		switch (this.cardNumber.toString()) {
		case "Two":
			cardValue = 2;
			break;
		case "Three":
			cardValue = 3;
			break;
		case "Four":
			cardValue = 4;
			break;
		case "Five":
			cardValue = 5;
			break;
		case "Six":
			cardValue = 6;
			break;
		case "Seven":
			cardValue = 7;
			break;
		case "Eight":
			cardValue = 8;
			break;
		case "Nine":
			cardValue = 9;
			break;
		case "Ten":
			cardValue = 10;
			break;
		case "Jack":
			cardValue = 10;
			break;
		case "Queen":
			cardValue = 10;
			break;
		case "King":
			cardValue = 10;
			break;
		case "Ace":
			cardValue = 11;
			break;
		}

		return cardValue;

	}

}
