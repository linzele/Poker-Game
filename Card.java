
enum Suit {
	Diamonds, Clubs, Hearts, Spades
}

enum CardFace {
	Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King
}

public class Card {

	private int CardRank;
	private int SuitRank;
	private int Value;

	private Suit suit;
	private CardFace face;

	public Card(Suit suit, CardFace face) {

		this.suit = suit;
		this.face = face;

	}

	public Suit getSuit() {

		return suit;
	}

	public CardFace getFace() {

		return face;
	}

	public int getValue() {
		return Value;
	}

	public int getSuitRank() {

		return SuitRank;

	}

	public int getCardRank() {

		return CardRank;
	}

	// Arranging Spade (highest) rank -> Diamonds (lowest) rank
	public void setSuitRank(Suit s) {

		if (s == Suit.Spades) {

			SuitRank = 4;

		}

		else if (s == Suit.Hearts) {

			SuitRank = 3;
		}

		else if (s == Suit.Clubs) {

			SuitRank = 2;
		}

		else {

			SuitRank = 1;
		}

	}

	// Arranging Pictures cards ranking (King -> Jacks + Ace)

	public void setCardRank(CardFace f) {

		if (f == CardFace.King) {

			CardRank = 13;
		}

		else if (f == CardFace.Queen) {

			CardRank = 12;
		}

		else if (f == CardFace.Jack) {

			CardRank = 11;
		}

		else if (f == CardFace.Ace) {

			CardRank = 1;
		}

		else {

			CardRank = Value;
		}

	}

	public void setValue(CardFace f) {
		if (f == CardFace.Ace) {
			Value = 1;
		} else if (f == CardFace.Two) {
			Value = 2;
		} else if (f == CardFace.Three) {
			Value = 3;
		} else if (f == CardFace.Four) {
			Value = 4;
		} else if (f == CardFace.Five) {
			Value = 5;
		} else if (f == CardFace.Six) {
			Value = 6;
		} else if (f == CardFace.Seven) {
			Value = 7;
		} else if (f == CardFace.Eight) {
			Value = 8;
		} else if (f == CardFace.Nine) {
			Value = 9;
		} else if (f == CardFace.Ten || f == CardFace.Jack || f == CardFace.Queen || f == CardFace.King) {
			Value = 10;
		} else {
			Value = 0;
		}
	}

	public String toString() {

		return "<" + suit + " " + face + ">";

	}

	public static void main(String[] args) {

		Card card = new Card(Suit.Spades, CardFace.King);
		card.setValue(card.getFace());
		System.out.println(card.getValue());
		System.out.println(card);

	}

}
