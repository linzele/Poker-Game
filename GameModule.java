
import java.util.*;

public class GameModule {

	private Scanner input = new Scanner(System.in);

	// declare variables

	private Dealer dealer;
	private Player player;
	public int dealerchips = 10;
	private int totalpot = 0;
	private boolean resume = true;
	private boolean playerIsAllIn = false;

	private String LINE = "==================================================";
	private String LINE2 = "--------------------------------------------------------------------------------";

	// the game will starts here!
	public GameModule() {
		dealer = new Dealer();
		player = new Player("IcePeak", "password", 100);
	}

	public void gamestart() {
		login();
		run();
	}

	public void login() {
		System.out.println("Poker Game");
		System.out.println(LINE);
		System.out.print("Enter Login name > ");

		try {

			String loginName = input.nextLine();

			System.out.print("Enter Password > ");
			String password = input.nextLine();
			if ((player.checkUsername(loginName) == true) && (player.checkPassword(password) == true)) {
				run(); // proceed with the game
				System.out.println(); // create a line space
			} else {
				System.out.println("Incorrect username or password, please try again.");
				System.out.println(); // create a line space
				login();
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please try again.");
			System.out.println(); // create a line space
			login();
		} catch (RuntimeException e) {
			System.out.println("Invalid input. Please try again.");
			System.out.println();
			login();
		}
	}

	public void run() {
		resume = true;
		System.out.println();
		System.out.println("Poker Game");
		System.out.println(LINE);
		System.out.println(player.getLoginName() + ", You have " + player.getChips() + " chips");
		System.out.println(LINE2);
		dealer.shuffleCards();
		System.out.println(LINE2);
		System.out.println("Dealer dealing cards - ROUND 1");
		System.out.println(LINE2);

		FirstRound();
	}

	public void FirstRound() {
		totalpot = 0;

		dealer.dealCardTo(player);
		dealer.dealCardTo(dealer);
		dealer.dealCardTo(player);
		dealer.dealCardTo(dealer);

		dealer.displayHiddenCard();

		player.displayHand();
		player.showTotalCardValue();

		ComparingCards();

		if (resume && player.getChips() > 0) {
			NextRound();
		} else {
			compareCardsFinalRound();
		}
	}

	public void NextRound() {
		int i = 2;
		while (i <= 4 && resume) {
			System.out.println("Dealer dealing cards - ROUND " + i);
			System.out.println(LINE);

			dealer.dealCardTo(player);
			dealer.dealCardTo(dealer);

			dealer.displayHiddenCard();

			player.displayHand();
			player.showTotalCardValue();

			if (player.getChips() > 0) {
				ComparingCards();
			}
			i++;
		}

		while (i == 4 && player.getCardsOnHand().size() < 5 && dealer.getCardsOnHand().size() < 5) {
			dealer.dealCardTo(player);
			dealer.dealCardTo(dealer);

		}

		if (player.getChips() == 0) {
			compareCardsFinalRound();
		} else if (i > 4) {
			GameEnd();
		}
	}

	private void ComparingCards() {
		if (playerIsAllIn) {
			System.out.println("Bet on table: " + totalpot);
		} else {
			int playerbet = player.getValue();
			int dealerbet = dealer.getValue();

			if (playerbet > dealerbet) {
				playerBet();
			} else if (playerbet < dealerbet) {
				dealerBet();
			} else {
				int playerCardRank = player.getCardRank();
				int dealerCardRank = dealer.getCardRank();

				if (playerCardRank > dealerCardRank) {
					playerBet();
				} else if (playerCardRank < dealerCardRank) {
					dealerBet();
				} else {
					int playerSuitRank = player.getSuitRank();
					int dealerSuitRank = dealer.getSuitRank();

					if (playerSuitRank > dealerSuitRank) {
						playerBet();
					} else {
						dealerBet();
					}
				}
			}
		}
	}

	private void compareCardsFinalRound() {
		System.out.println("Commencing Final / Show Hand Round.");
		System.out.println("Game End - Dealer reveal hidden cards");
		System.out.println(LINE);

		while ((player.getCardsOnHand().size() < 5) && (dealer.getCardsOnHand().size() < 5)) {
			dealer.dealCardTo(player);
			dealer.dealCardTo(dealer);
		}

		dealer.displayHand();
		dealer.showTotalCardValue();
		System.out.println();

		player.displayHand();
		player.showTotalCardValue();
		System.out.println();

		if (dealer.getTotalCardsValue() > player.getTotalCardsValue()) {
			System.out.println("Dealer Wins");
		} else if (player.getTotalCardsValue() > dealer.getTotalCardsValue()) {
			System.out.println(player.getLoginName() + " Wins");
			player.addChips(totalpot);
		} else if (player.getTotalCardsValue() == dealer.getTotalCardsValue()) {
			System.out.println("It's a tie! ");
			player.addChips(totalpot / 2);
		}

		System.out.println();
		End();
	}

	public void playerBet() {

		if (player.getChips() == 0) {
			compareCardsFinalRound();

		}

		else {

			System.out.print("Do you want to [C]all or [Q]uit?: ");
			String playerinput = input.next().toUpperCase();

			while (!playerinput.equals("C") && !playerinput.equals("Q")) {
				System.out.println("Please enter either [C](Call) or [Q](Quit):");
				playerinput = input.next().toUpperCase();
			}

			if (playerinput.equals("C")) {
				if (player.getChips() == 0) {
					compareCardsFinalRound();

					resume = true;
					return;
				}

				System.out.print("Player call, state bet: ");
				int amount = 0;

				boolean validBet = false;

				while (!validBet) {
					try {
						amount = input.nextInt();
						if (amount <= 0) {
							throw new RuntimeException("");
						} else if (amount > player.getChips()) {
							throw new RuntimeException("You do not have enough chips to make that bet.");

						} else {
							validBet = true;
						}
					} catch (InputMismatchException e) {
						System.out.print("You have entered an invalid character. Please enter a valid amount: ");
						input.nextLine();
					} catch (RuntimeException e) {
						System.out.println(e.getMessage());
						System.out.println("Please enter a valid amount.");
						System.out.print("Player call, state bet: ");
					}
				}

				player.deductChips(amount);
				totalpot = totalpot + (amount * 2);
				System.out.println("Bet on table: " + totalpot);
				System.out.println(LINE);

			} else if (playerinput.equals("Q")) {
				quit();
			}
		}

	}

	public void playerBetFirstRound() {

		if (player.getChips() == 0) {
			compareCardsFinalRound();

		}

		System.out.print("Player call, state bet: ");
		int amount = 0;

		while (true) {
			try {
				amount = input.nextInt();
				if (amount <= 0) {
					throw new RuntimeException("Please enter an amount greater than 0.");
				} else if (amount > player.getChips()) {
					throw new RuntimeException("You don't have enough chips to make that bet.");

				} else {
					player.deductChips(amount);
					totalpot += amount * 2;
					System.out.println("Bet on table: " + totalpot);
					System.out.println(LINE);
					break;
				}
			} catch (InputMismatchException e) {
				System.out.print("You have entered an invalid character. Please enter a valid amount: ");
				input.nextLine();
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
				System.out.print("Player call, state bet: ");
			}
		}
	}

	private void dealerBet() {

		if ((player.getChips() == 0)) {
			compareCardsFinalRound();

		}
		else if (player.getChips() > 0) {
			System.out.println("Dealer call, state bet: 10");
			int amount = 10;

			System.out.print("Do you want to follow? [Y/N]: ");

			try {

				String dealerinput = input.next();
				if (dealerinput.equalsIgnoreCase("Y") || dealerinput.equalsIgnoreCase("N")) {
					char dealerResume = Character.toUpperCase(dealerinput.charAt(0));
					if ((dealerResume == 'Y' || dealerResume == 'y') && player.getChips() >= amount) {
						player.deductChips(amount);
						totalpot = totalpot + (amount * 2);
						System.out.println("Bet on table: " + totalpot);
						System.out.println(LINE);

					} else if ((dealerResume == 'Y' || dealerResume == 'y') && player.getChips() < amount
							&& player.getChips() != 0) {
						amount = player.getChips();
						player.deductChips(amount);
						totalpot = totalpot + (amount * 2);
						System.out.println("As player has insufficient chips to follow, Dealer will bet same amount as player.");
						System.out.println("Commencing Final / Show Hand Round.");
						System.out.println("Bet on table: " + totalpot);
						System.out.println("");
						
						playerIsAllIn = true;

					}

					 else if (dealerResume == 'N' || dealerResume == 'n') {
						resume = false;
						quit();
					} else {
						System.out.println("\nInvalid Input. Try again.");
						dealerBet();
					}
				} else {
					System.out.println("Please enter either [Y] or [N]");
					dealerBet();
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid option.");
				dealerBet();
			}
		}
	}

	private void quit() {
		resume = false;
		System.out.println();
		System.out.println(player.getLoginName() + " quits. Dealer wins.");
		End();
	}

	private void End() {
		System.out.println(player.getLoginName() + ", You have " + player.getChips() + " chips");
		System.out.println("Dealer shuffles used cards and place behind the deck.");
		player.clearHand();
		dealer.clearHand();
		dealer.collectCards();
		System.out.println(LINE);
		playerIsAllIn = false;
		PlayAgain();
	}

	public void PlayAgain() {
		try {
			if (player.getChips() > 0) {
				System.out.print("Next Game? [Y/N]: ");
				String next = input.next().trim().toLowerCase();
				input.nextLine();
				if (next.equals("y")) {
					login();
				} else if (next.equals("n")) {
					System.out.println("Thanks for playing!");
					resume = false;
				} else {
					System.out.println("Invalid input, try again.");
					PlayAgain();
				}
			} else {
				System.out.println("You do not have enough balance to play. Thanks for playing!");
				resume = false;
			}
		} catch (RuntimeException RE) {
			System.out.println("Invalid Input. try again!");
			PlayAgain();
		} catch (StackOverflowError SOE) {
			System.out.println("Invalid Input. try again!");
			PlayAgain();
		}

	}

	public void GameEnd() {
		System.out.println("Game End - Dealer reveal hidden cards");
		System.out.println(LINE);

		dealer.displayHand();
		dealer.showTotalCardValue();
		System.out.println();

		player.displayHand();
		player.showTotalCardValue();
		System.out.println();

		if (dealer.getTotalCardsValue() > player.getTotalCardsValue()) {
			System.out.println("Dealer Wins");
		} else if (player.getTotalCardsValue() > dealer.getTotalCardsValue()) {
			System.out.println(player.getLoginName() + " Wins");
			player.addChips(totalpot);
		} else if (player.getTotalCardsValue() == dealer.getTotalCardsValue()) {
			System.out.println("It's a tie! ");
			player.addChips(totalpot / 2);
		}

		End();
	}

	public static void main(String[] args) {
		GameModule app = new GameModule();
		app.login();
	}
}
