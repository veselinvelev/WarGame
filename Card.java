package warGame;

class Card {

	private final byte J = 11; // Jack
	private final byte Q = 12; // Queen
	private final byte K = 13; // King
	private final byte A = 14; // Ace

	private String cardSing;
	private int cardValue;

	Card(int value) {

		if (value >= 2 && value <= A) {

			if (value >= 2 && value <= 10) {
				this.cardValue = value;
				this.cardSing = "" + value;
			}

			switch (value) {
			case J:
				this.cardValue = value;
				this.cardSing = "J";
				break;
			case Q:
				this.cardValue = value;
				this.cardSing = "Q";
				break;

			case K:
				this.cardValue = value;
				this.cardSing = "K";
				break;

			case A:
				this.cardValue = value;
				this.cardSing = "A";
				break;

			default:
				break;
			}

		} else {
			System.out.println("Invalid card value");
		}

	}

	void printCard() {
		System.out.print(this.cardSing);
	}

	int getCardValue() {
		return this.cardValue;
	}

	String getCardSing() {
		return this.cardSing;
	}

}
