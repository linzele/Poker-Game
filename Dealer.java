
public class Dealer extends Player {

	private Deck deck;
	

	public void Card() {

	}

	public Dealer() {
		super("Dealer", "", 0);
		deck = new Deck();

	}

	public void shuffleCards() {

		System.out.println("Game starts - Dealer shuffle deck.");
		deck.shuffle();

	}

	public void dealCardTo(Player player) {

		Card card = deck.dealCard();
		player.addCard(card);

	}
	


	public void displayHiddenCard() {
		System.out.println(getLoginName());
		System.out.print("<HIDDEN CARD> ");
		for (int i = 1; i < getCardsOnHand().size(); i++) {
			System.out.print(getCardsOnHand().get(i) + " ");
		}
		System.out.println();
	}

	public void clearHand() {
		cardsOnHand.clear();
	}

	public void collectCards() {
		deck.appendCards();
	}


}
