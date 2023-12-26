
import java.util.*;

public class Player extends User {

	private int chips;

	protected ArrayList<Card> cardsOnHand;

	public Player(String loginName, String password, int chips) {

		super(loginName, password);
		this.chips = chips;
		this.cardsOnHand = new ArrayList<Card>();

	}

	public void addChips(int amount) {
		this.chips += amount;

	}

	public void deductChips(int amount) {
		this.chips -= amount;
		System.out.println("IcePeak, You are left with " + getChips() + " chips");
	}

	public int getChips() {
		return this.chips;
	}

	public void addCard(Card card) {

		cardsOnHand.add(card);

	}

	public void displayHand() {
		System.out.println(getLoginName());
		for (Card card : cardsOnHand) {
			System.out.print(card + " ");
		}
		System.out.println();
	}

	public void clearHand() {
		cardsOnHand.clear();
	}

	public int getValue() {
		int Value = cardsOnHand.get(cardsOnHand.size() - 1).getValue();
		return Value;
	}

	public int getCardRank() {

		return cardsOnHand.get(cardsOnHand.size() - 1).getCardRank();

	}

	public int getSuitRank() {

		return cardsOnHand.get(cardsOnHand.size() - 1).getSuitRank();

	}

	
	public void showCardsOnHand() {

		System.out.println(getLoginName());
		for (Card card : cardsOnHand) {
			System.out.println(card + "");

		}

		System.out.println();
	}

	public void showTotalCardValue() {

		System.out.println("Value:" + getTotalCardsValue());
	}

	public int getTotalCardsValue() {

		int total = 0;
		for (Card card : cardsOnHand) {
			total += card.getValue();
		}
		return total;
	}

	public ArrayList<Card> getCardsOnHand() {

		return cardsOnHand;
	}

	// Just for testing
	public static void main(String[] args) {

		Player player = new Player("Ranger", "password", 50);
		Deck deck = new Deck();
		deck.shuffle();
		Card card1 = deck.dealCard();
		Card card2 = deck.dealCard();
		Card card3 = deck.dealCard();
		player.addCard(card1);
		player.addCard(card2);
		player.addCard(card3);
		player.showCardsOnHand();
		player.showTotalCardValue();
		deck.showCards();

	}

}
