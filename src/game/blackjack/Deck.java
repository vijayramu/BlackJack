package game.blackjack;
import java.util.*;

public class Deck {

	/* 
	 * Number of cards in a 1 suit, if we need more decks to deal 
	 * multiply number of decks * 13
	 */
	private int numberCardsinSuit = 13;
	// default is 1 deck so 52 cards
	private int cardsInDeck = 4 * numberCardsinSuit;
	// cards will have default stack and shuffled cards will shuffle randomly
	private Cards[] cards;
	private int idx = 0;
	Random randomNumber = new Random();

	/*
	 * Constructor to deal a new set for playing
	 */
	public Deck() {
		this.cards = new Cards[cardsInDeck];
		this.loadCards();
		this.shuffleCards();
	}

	/*
	 * Load the cards in order first to shuffle in next step
	 */
	private void loadCards() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < numberCardsinSuit; j++) {
				this.cards[idx] = new Cards(Suit.values()[i],
						Number.values()[j]);
				idx++;
			}
		}
	}

	/*
	 * shuffle the cards in deck randomly
	 */
	private void shuffleCards() {
		for (int i = 0; i < cardsInDeck; i++) {
			Cards swap;
			int rnd;

			rnd = randomNumber.nextInt(cardsInDeck - 1);
			swap = this.cards[i];
			this.cards[i] = this.cards[rnd];
			this.cards[rnd] = swap;
		}
	}

	/*
	 * get the next card from the stack remove the card from the stack and shift
	 * the cards leaving last ones blank
	 */
	public Cards getTopOfDeck() {
		// Cards nextCard = this.shuffledCards[0];
		Cards nextCard = this.cards[0];

		for (int i = 1; i < this.cardsInDeck; i++) {
			this.cards[i - 1] = this.cards[i];
		}

		cards[this.cardsInDeck - 1] = null;

		this.cardsInDeck--;

		return nextCard;

	}

	/*
	 * get the remaining cards in stack to determine 
	 * if it needs to be shuffled again
	 */
	public int avalilableCards() {
		return this.cardsInDeck;
	}

}
