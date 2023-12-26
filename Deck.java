
import java.util.*;

public class Deck {

	private ArrayList<Card> cards;
	private ArrayList<Card> HandCards;
	private Suit[] suit = new Suit[] { Suit.Diamonds, Suit.Clubs, Suit.Hearts, Suit.Spades };
	private CardFace[] face = new CardFace[] { CardFace.Ace, CardFace.Two, CardFace.Three, CardFace.Four, CardFace.Five,
			CardFace.Six, CardFace.Seven, CardFace.Eight, CardFace.Nine, CardFace.Ten, CardFace.Jack, CardFace.Queen,
			CardFace.King };

	public Deck() {

		cards = new ArrayList<Card>();
		HandCards = new ArrayList<Card>();
		for (CardFace faces : face) {
			for (Suit suits : suit) {
				Card card = new Card(suits, faces);
				card.setValue(faces);
				card.setCardRank(faces);
				card.setSuitRank(suits);
				cards.add(card);
			}
		}
	}

	public void shuffle() {

		Collections.shuffle(cards);
	}

	public void removeAllCards(ArrayList<Card> cardsToRemove) {
		this.cards.removeAll(cardsToRemove);
	}

	public void appendCards() { // get back the cards

		for (Card card : HandCards) {
			cards.add(card);
		}
		HandCards.removeAll(HandCards);
	}

	public Card dealCard() { // dealing the cards
		HandCards.add(cards.get(0));
		return cards.remove(0);

	}

	public void showCards() {

		for (Card card : cards) {
			System.out.println(card.toString());
		}
	}

	// Just for testing
	public static void main(String[] args) {
		Deck deck = new Deck();
		deck.shuffle();
		deck.showCards();
		Card card1 = deck.dealCard();
		Card card2 = deck.dealCard();
		Card card3 = deck.dealCard();
		System.out.println();
		System.out.println(card1);
		System.out.println(card2);
		System.out.println(card3);
		System.out.println();

		System.out.println();
		deck.showCards();

	}
}
