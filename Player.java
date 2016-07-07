package warGame;

import warGame.Card;

class Player {
	private String name;
	private Card[] deck;
	private Card[] wonCards;
	private int nextCard;
	private int addCardIndex;

	Player(int decSize) {

		this.name = "Player";

		if (decSize > 0 && decSize % 2 == 0) {

			this.deck = new Card[decSize / 2];
			this.wonCards = new Card[decSize];
			this.nextCard = this.deck.length - 1;
			this.addCardIndex = 0;
		} else {
			System.out.println("Invalid deck size. The size must be positive even number!");
		}

	}

	Player(String name, int decSize) {

		this(decSize);

		if (name != null & !name.equals("")) {
			this.name = name;
		} else {
			System.out.println("Invalid player name.");
		}
	}

	int remmainingCards() {
		return (this.nextCard >= 2) ? 3 : this.nextCard + 1;
	}

	Card drawNextCard() {

		Card nextDrawnCard;

		if (this.deck[nextCard] != null) {
			nextDrawnCard = this.deck[this.nextCard];
			this.deck[this.nextCard] = null;
			this.nextCard--;

		} else {
			System.out.println("The deck is not complete or there is no next card");
			return null;
		}

		return nextDrawnCard;
	}

	void takeTheHand(Card[] hand) {

		if (hand != null) {

			// take a card from the hand
			for (int card = 0; card < hand.length; card++) {

				if (hand[card] != null) {
					// put the card on the first free position
					for (int index = 0; index < hand.length; index++) {
						if (this.wonCards[index] == null) {
							this.wonCards[index] = hand[card];
							break;
						}
					}
				}
			}

		} else {
			System.out.println("Invalid hand.");

		}

	}

	void printDeck() {

		for (int card = 0; card < deck.length; card++) {
			if (this.deck[card] != null) {
				System.out.print(this.deck[card].getCardSing() + " ");
			} else {
				System.out.println(" " + this.name + " \'s deck is not complete!");
				break;
			}
		}
		System.out.println();
		// System.out.println(this.deck.length);

	}

	int getTheCardNumber() {
		int cards = 0;
		for (int card = 0; card < this.wonCards.length; card++) {
			if (this.wonCards[card] != null) {

				cards++;
			} else {

				break;
			}
		}

		return cards;

	}

	void printWonCards() {
		for (int card = 0; card < this.wonCards.length; card++) {
			if (this.wonCards[card] != null) {
				System.out.print(this.wonCards[card].getCardSing() + " ");
			} else {
				break;
			}
		}
	}

	String getName() {
		return name;
	}

	void addACard(Card card) {

		if (card != null) {
			this.deck[addCardIndex++] = card;

		} else {
			System.out.println("Invalid card!");
		}

	}
}
