package warGame;

import war.Result;

public class WarGame {

	static final int MIN_POWER = 2; // card min power
	static final int MAX_POWER = 14; // card max power
	static final int OCCURRENCES_OF_A_CARD = 4;
	static final int NUMBER_OF_CARDS = (MAX_POWER - MIN_POWER + 1) * OCCURRENCES_OF_A_CARD;

	static Card[] shuffleTheDeck() {
		Card[] deck = new Card[NUMBER_OF_CARDS];
		// card powers 2 - 14
		for (int cardValue = MIN_POWER; cardValue <= MAX_POWER; cardValue++) {

			// four cards for every power
			for (int card = 1; card <= OCCURRENCES_OF_A_CARD; card++) {

				// random position in the deck
				int position = (int) (Math.random() * (deck.length));

				// take next free position if the current one is not free
				while (deck[position] != null) {
					position++;
					if (position == deck.length) {
						position = 0;
					}
				}
				// create and add a card with power card value in the deck
				deck[position] = new Card(cardValue);
			}

		}

		return deck;
	}

	static void drawTheCards(Card[] originalDeck, Player playerOne, Player playerTwo) {

		if (playerOne == null || playerTwo == null) {
			System.out.println("Invalid players. Please select valid ones!");
			return;
		}

		for (int card = 0; card < originalDeck.length; card++) {
			// System.out.println(card);
			if (card % 2 == 0) {
				playerOne.addACard(originalDeck[card]);
			} else {
				playerTwo.addACard(originalDeck[card]);
			}

		}

	}

	static Result turnWinner(Card playerOneCard, Card playerTwoCard) {

		if (playerOneCard.getCardValue() > playerTwoCard.getCardValue()) {
			return Result.P1;
		}

		if (playerOneCard.getCardValue() < playerTwoCard.getCardValue()) {
			return Result.P2;
		}

		return Result.WAR;

	}

	public static void main(String[] args) {

		Card[] deck = shuffleTheDeck();

		Player playerOne = new Player("P1", NUMBER_OF_CARDS);
		Player playerTwo = new Player("P2", NUMBER_OF_CARDS);
		drawTheCards(deck, playerOne, playerTwo);

		boolean isWar = false;
		Card[] hand = new Card[NUMBER_OF_CARDS];
		int card = 0;
		Card playerOneCard = null;
		Card playerTwoCard = null;

		// for (int i = 0; i < deck.length; i++) {
		// System.out.print(deck[i].getCardSing() + " ");
		// }
		//
		// System.out.println();
		// playerOne.printDeck();
		// playerTwo.printDeck();
		// System.out.println();

		do {

			if (!isWar) {
				card = 0;
				hand = new Card[NUMBER_OF_CARDS];
				playerOneCard = playerOne.drawNextCard();
				hand[card++] = playerOneCard;
				playerTwoCard = playerTwo.drawNextCard();
				hand[card++] = playerTwoCard;
				System.out.print(playerOneCard.getCardSing() + " " + playerTwoCard.getCardSing());

			}

			Result result = turnWinner(playerOneCard, playerTwoCard);
			System.out.println(" " + result);

			if (result == Result.P1) {
				playerOne.takeTheHand(hand);
				isWar = false;
			} else {
				if (result == Result.P2) {
					playerTwo.takeTheHand(hand);
					isWar = false;
				} else {

					int end = playerOne.remmainingCards();

					if (end <= 0) {
						break;
					}

					if (isWar) {
						// System.out.println("Already in War");
						end = 1;
					}

					for (int index = 0; index < end; index++) {

						playerOneCard = playerOne.drawNextCard();
						hand[card++] = playerOneCard;
						playerTwoCard = playerTwo.drawNextCard();
						hand[card++] = playerTwoCard;
						System.out.print(" " + playerOneCard.getCardSing() + " " + playerTwoCard.getCardSing() + "|");
					}

					isWar = true;
				}
			}

			if (playerOne.remmainingCards() <= 0 && playerTwo.remmainingCards() <= 0 && !isWar) {
				break;
			}
		} while (true);

		int p1Cards = playerOne.getTheCardNumber();
		System.out.println();
		int p2Cards = playerTwo.getTheCardNumber();

		if (p1Cards > p2Cards) {
			System.out.println(playerOne.getName() + " is the winner!");

		} else {

			if (p1Cards < p2Cards) {
				System.out.println(playerTwo.getName() + " is the winner!");

			} else {
				System.out.println("Tie!");

			}

		}

		playerOne.printWonCards();
		System.out.println();
		playerTwo.printWonCards();

	}

}
