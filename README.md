# Poker Game 

Poker Game is the core interface involving a dealer (the program) and a single human player . It provides a platform for players to engage in gameplay by accessing game features, attempting challenges, and managing their progress. Through this game, players can log in, play the game, and utilize game-specific functionalities tailored to their gaming experience. The game uses standard playing cards, assigning values based on card ranks and suits. Players aim to achieve the highest accumulated value in their hand through multiple rounds of betting. Good luck and enjoy!



# **Prerequisites**



1.  **Java Development Kit (JDK)**: Ensure you have Java installed on your system. You can download and install it from the official Oracle Java website.

## 
1.  **Setup Environment:**
    
    -   Open a terminal or command prompt and navigate to the directory containing the downloaded Java files.
2.  **Compile the Java Files:**
    
    -   Use the Java compiler (`javac`) to compile the Java source files. Run the following command:
   ```bash
		    javac GameModule.java
```

    - This command compiles all Java files (`*.java`) in the directory.

3. **Run the Program:**

    -   Once the compilation is successful, execute the Java program. Use the following command:
```bash
		    java GameModule.java
```        
    -   Replace `GameModule` with the name of the main Java file that contains the `main()` method if it's different from `GameModule`.


## Game Functions (Administration Module)

**Objective:** The primary goal of the Administration Module is to enable administrators to manage player accounts and game settings efficiently. It offers functionalities related to player account creation, deletion, viewing player details, managing chips, and changing passwords.

**Admin Functions:**

1.  **Create a Player:**
    -   Allows the creation of new player accounts by specifying login names, hashed passwords (in SHA-256), and initial chip amounts.
2.  **Delete a Player:**
    -   Enables the deletion/removal of existing player accounts from the game.
3.  **View All Players:**
    -   Displays a list of all registered players along with their login names, hashed passwords, and chip balances.
4.  **Issue Chips to a Player:**
    -   Allows administrators to add more chips to a player's account.
5.  **Reset Player’s Password:**
    -   Provides the ability to reset a player's password.
6.  **Change Administrator’s Password:**
    -   Allows the admin to update their own password.
7.  **Logout:**
    -   Logs the admin out of the Administration Module.
## User Details (Stored in Player.bin)



| Login Name | Password in SHA-256|  Chips|
| ----------------------------- | ------------------------------- | ---------------------------|
|BlackRanger| 6cf615d5bcaac778352a8f1f3360d23f02f34ec182e259897fd6ce485d7870d4|1000
|BlueKnight| 5906ac361a137e2d286465cd6588ebb5ac3f5ae955001100bc41577c3d751764|1500
|IcePeak| b97873a40f73abedd8d685a7cd5e5f85e4a9cfb83eac26886640a0813850122b|100
|GoldDigger| 8b2c86ea9cf2ea4eb517fd1e06b74f399e7fec0fef92e3b482a6cf2e2b092023|2200


## Admin Details (Stored in Admin.txt)

Refer to Admin.txt

- Link to decrypt SHA-256: 

https://md5decrypt.net/en/Sha256/


