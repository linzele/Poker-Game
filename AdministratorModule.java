import java.util.*;
import java.io.*;

public class AdministratorModule {
	private static ArrayList<Player> players = new ArrayList<Player>(); // ArrayList<User.class> new object
	private static String LINE = "=====================================================================";
	private static String LINE2 = "==============================================================================================";
	private static String THIN = "---------------------------------------------------------------------";

	// Default constructor
	public AdministratorModule(String logInName, String password, int chips) {

	}

	// ArrayList of Players
	public static ArrayList<Player> getPlayers() {
		return players;
	}

	// Admin Login

	public static void AdminLogin() {
		System.out.println(LINE);
		System.out.println("Administration Module");
		System.out.println(LINE);
		System.out.println("Please Enter Your Password:");
		Scanner input = new Scanner(System.in);
		String adminPassword = input.nextLine();
		String hashedUserInput = Utility.getHash(adminPassword);
		Boolean correctLogin = false;

		while (!correctLogin) {
			if (hashedUserInput.equals(readInToAdminTextFile())) {
				correctLogin = true;
				System.out.println("Login successful. Welcome to the administrator module.");
			} else {
				System.out.println("Incorrect password. Please try again.");
				adminPassword = input.nextLine();
				hashedUserInput = Utility.getHash(adminPassword);
			}
		}
	}

	// Running of Program
	public static void run() {
		Scanner input = new Scanner(System.in);
		boolean gameStart = true;

		while (gameStart) {
			System.out.println(LINE);
			System.out.println("Admin Interface - Logged In ");
			System.out.println(THIN);
			System.out.println("1. Create Player");
			System.out.println(THIN);
			System.out.println("2. Delete Player");
			System.out.println(THIN);
			System.out.println("3. Display All Players ");
			System.out.println(THIN);
			System.out.println("4. Top Up Player's Chips");
			System.out.println(THIN);
			System.out.println("5. Reset Player Password ");
			System.out.println(THIN);
			System.out.println("6. Change Admin Password ");
			System.out.println(THIN);
			System.out.println("7. Logout ");
			System.out.println(THIN);
			System.out.print("Please enter your choice:");

			int choice;
			try {
				choice = Integer.parseInt(input.nextLine());
				System.out.println(LINE);
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter an integer between [1] and [7].");
				continue;
			}

			switch (choice) {
			case 1:
				createPlayers();
				UpdatingPlayerBin();
				break;
			case 2:
				deletePlayer();
				UpdatingPlayerBin();
				break;
			case 3:
				DisplayAllPlayers();
				break;
			case 4:
				TopUpChipsToPlayers();
				UpdatingPlayerBin();
				break;
			case 5:
				resetPlayerPassword();
				UpdatingPlayerBin();
				break;
			case 6:
				changeAdminPassword();
				break;

			case 7:
				gameStart = false;
				break;

			default:
				System.out.println("Invalid choice. Please enter a number between 1 and 7.");
			}
		}
		System.out.println("");
		System.out.println("Log Out Successful");
		System.out.println("Exiting Admin Interface.");
		System.out.println(LINE);
	}

	// Creating new players
	public static void createPlayers() {
		System.out.println("");
		System.out.println("Creating Players");
		System.out.println(LINE);
		Scanner input = new Scanner(System.in);
		boolean addMore = true;

		while (addMore) {
			try {
				String loginName = "";

				while (loginName.isEmpty()) {
					System.out.println("Please enter the player's login name:");
					loginName = input.nextLine();
					boolean loginNameExists = false;

					// Check if login name already exists
					for (Player player : getPlayers()) {
						if (player.getLoginName().equals(loginName)) {
							loginNameExists = true;
							break;
						}
					}

					if (loginName.isEmpty()) {
						System.out.println("Login Name cannot be empty, Please Try again.");
					} else if (loginNameExists) {
						System.out.println("Login name already exists. Please choose a different login name.");
						loginName = "";
					}
				}

				String password = "";

				while (password.isEmpty()) {
					System.out.println("Please enter the player's password:");
					password = input.nextLine();

					if (password.isEmpty()) {
						System.out.println("Password cannot be empty. Please Try Again.");
					}
				}

				int chipsToStart = 0;
				while (chipsToStart <= 0) {
					System.out.println("Please enter the number of chips the player will start with:");
					chipsToStart = input.nextInt();
					input.nextLine();

					if (chipsToStart <= 0) {
						System.out.println("Please enter an amount more than 0. Try Again.");
						// input.nextLine();

					}
				}

				Player player = new Player(loginName, password, chipsToStart);
				getPlayers().add(player);

				System.out.println("Player created successfully.");

				while (true) {
					System.out.println("Do you want to add another player? [Y/N]");
					String response = input.nextLine();
					if (response.equalsIgnoreCase("N")) {
						addMore = false;
						break;
					}

					else if (response.equalsIgnoreCase("Y")) {
						break; // exit the loop
					} else {
						System.out.println("Invalid input. Please enter Y or N.");
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid value.");
				input.nextLine();
			}
		}

		UpdatingPlayerBin();
	}

	// Deleting of Players
	public static void deletePlayer() {
		Scanner input = new Scanner(System.in);
		DisplayAllPlayers();
		System.out.println("");
		System.out.println("Deleting Players");
		System.out.println(LINE);
		System.out.println();

		boolean validInput = false;
		String enterName = "";
		while (!validInput) {
			System.out.print("Enter the player username that you would like to delete: ");
			enterName = input.nextLine();

			for (Player player : getPlayers()) {
				if (player.getLoginName().equals(enterName)) {
					System.out.printf("Player deleted, returning to Admin Interface.\n");
					validInput = true;
					break;
				}
			}

			if (!validInput) {
				System.out.println("Invalid input. Please enter a valid player username.");
			}
		}

		for (Player player : getPlayers()) {
			if (player.getLoginName().equals(enterName)) {
				getPlayers().remove(player);
				break;
			}
		}

		UpdatingPlayerBin();
	}

	public static void TopUpChipsToPlayers() {
		DisplayAllPlayers();
		Scanner input = new Scanner(System.in);
		System.out.println("");
		System.out.println(LINE);
		System.out.println("Choose Player To Top Up Chips");
		System.out.println(LINE);
		System.out.println("Please type in the Player name to add chips: ");
		String playerName = input.nextLine();

		boolean loginNameExists = false;

		// Check if login name already exists
		for (Player player : getPlayers()) {
			if (player.getLoginName().equals(playerName)) {
				loginNameExists = true;
				break;
			}
		}

		while (!loginNameExists) {
			// Check if login name already exists
			for (Player player : getPlayers()) {
				if (player.getLoginName().equals(playerName)) {
					loginNameExists = true;
					break;
				}
			}

			if (!loginNameExists) {
				System.out.println("Player name not found in database. Please try again.");
				System.out.println("Please type in the Player name to add chips: ");
				playerName = input.nextLine();
			}
		}

		System.out.println("Please enter the amount of chips to add: ");

		int chipsToEnter = 0;
		boolean validInput = false;

		while (!validInput) {
			String inputStr = input.nextLine().trim();
			if (inputStr.isEmpty()) {
				System.out.println("Invalid input. Please enter a valid number.");
			} else {
				try {
					chipsToEnter = Integer.parseInt(inputStr);
					if (chipsToEnter <= 0) {
						System.out.println("Please enter a number greater than 0.");
					} else {
						validInput = true;
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid input. Please enter a valid number.");
				}
			}
		}

		boolean foundPlayer = false;
		for (Player player : getPlayers()) {
			if (player.getLoginName().equals(playerName)) {
				player.addChips(chipsToEnter);
				System.out.println("Chips added, returning to Admin Interface.");
				foundPlayer = true;
				break;
			}
		}

		if (!foundPlayer) {
			System.out.println("Please enter correct player name.");
		}

		UpdatingPlayerBin();
	}

	public static void resetPlayerPassword() {
		DisplayAllPlayers();
		System.out.println("");
		System.out.println("Reset Player Password");
		System.out.println(LINE);
		Scanner input = new Scanner(System.in);
		boolean passwordUpdated = false;

		while (!passwordUpdated) {
			System.out.print("Please enter player's name: ");
			System.out.println("");
			String playerName = input.nextLine();
			boolean playerFound = false;

			for (Player player : getPlayers()) {
				if (player.getLoginName().equals(playerName)) {
					System.out.print("Please enter the new password: ");
					String newPassword = input.nextLine();
					String hashedPassword = Utility.getHash(newPassword);
					player.setPassword(hashedPassword);
					System.out.println("Password updated successfully.");
					playerFound = true;

				}
			}

			if (!playerFound) {
				System.out
						.println("The player " + playerName + " does not exist. Please enter an existing player name.");
			} else {
				passwordUpdated = true;
			}
		}

		UpdatingPlayerBin();
	}

	public static void DisplayAllPlayers() {

		{
			System.out.println("");
			System.out.println(LINE2);
			System.out.println("");
			System.out.println("View All Players");
			System.out.println(LINE2);

			System.out.printf("%-15s%-70s%-10s%n", "LOGIN NAME", "PASSWORD HASH", "CHIPS");
			System.out.println("");

			for (Player player : players) {
				System.out.printf("%-15s%-70s%-10s%n", player.getLoginName(), player.getHashedPassword(),
						player.getChips());
				System.out.println("");
			}

			System.out.println(
					"==============================================================================================");
		}
	}

	public static void changeAdminPassword() {

		System.out.println("");
		System.out.println("Changing Admin Password");
		System.out.println(LINE);
		boolean loggedIn = false;
		Scanner input = new Scanner(System.in);

		while (!loggedIn) {
			System.out.println("Please enter the current admin password:");
			String currentPassword = input.nextLine();
			String hashedCurrentPassword = Utility.getHash(currentPassword);

			if (hashedCurrentPassword.equals(readInToAdminTextFile())) {
				loggedIn = true;
			} else {
				System.out.println("Incorrect password, please try again.");
			}
		}

		boolean validInput = false;
		String newPassword;

		while (!validInput) {
			System.out.println("Please enter the new admin password:");
			newPassword = input.nextLine();
			String hashedNewPassword = Utility.getHash(newPassword);

			if (!hashedNewPassword.equals(readInToAdminTextFile())) {
				try (PrintWriter p = new PrintWriter(new FileWriter("admin.txt"))) {
					p.println(hashedNewPassword);
					System.out.println("Password has been changed successfully.");
					validInput = true;
				} catch (IOException io) {
					System.out.println("Error, please input again.");
				}
			} else {
				System.out.println("The new password the same as the old method. Please enter a different password.");
			}
		}
	}

	// Get Login Name Of Players
	public static String getLoginName() {
		String username = "";
		for (Player player : getPlayers()) {
			username = player.getLoginName();

		}
		return username;
	}

	// Get Password Of Players

	public static String getPassword() {
		String password = "";
		for (Player player : getPlayers()) {
			password = player.getHashedPassword();

		}
		return password;
	}

	// Loading data from player.bin

	public static void loadPlayersFromBin() {

		try {
			FileInputStream file = new FileInputStream("players.bin");
			ObjectInputStream oi = new ObjectInputStream(file);

			boolean endOfFile = false;
			while (!endOfFile) {
				try {
					Player player = (Player) oi.readObject();
					getPlayers().add(player);
				} catch (EOFException eof) {
					endOfFile = true;
				}
			}
			oi.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		// System.out.println("Player Information loaded.");
		System.out.println("");

	}

	// Updating / Saving data to player.bin

	public static void UpdatingPlayerBin() {
		try {
			FileOutputStream file = new FileOutputStream("players.bin");
			ObjectOutputStream os = new ObjectOutputStream(file);

			for (Player player : getPlayers()) {
				os.writeObject(player);
			}
			os.close();
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	public static String readInToAdminTextFile() {

		String line = "";
		try {
			File file = new File("admin.txt");
			Scanner reader = new Scanner(file);

			while (reader.hasNextLine()) {
				line = reader.nextLine();

			}
			reader.close();

		} catch (FileNotFoundException ex) {
			System.out.println("Unable to find file for reading.");
		}
		return line;
	}

	public static void main(String[] args) {

		loadPlayersFromBin();
		AdminLogin();
		run();
	}
}
