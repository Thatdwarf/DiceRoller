package thatDwarf.diceRoller;

import java.util.Random;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) {

        System.out.println("Do you wish to roll or battle");
        System.out.println("To roll enter Roll, To battle enter Battle");
        Scanner user_input = new Scanner(System.in);            // Input setup
        String rollOrBattle = user_input.next();

        switch (rollOrBattle) {
            case "Roll":
                Dice Dice1 = new Dice(0,0,0);                // create new dice object
                diceRoll(Dice1);                        // perform diceRoll method on created Dice
                break;

            case "Battle":
                battleSetup();
                break;

            default:
                System.out.println("Error Incorrect Input");
        }


    }

    public static void diceRoll(Dice in) {
            int total = 0;
            int critTotal = 0;
            int failTotal = 0;

            int diceAmount = in.getDiceAmount();
            int diceSides = in.getDiceSides();                  // takes values from Dice1 object
            int diceMod = in.getDiceMod();

            if (diceAmount == 0 && diceSides == 0 && diceMod == 0) {

                Scanner user_input = new Scanner(System.in);            // Input setup

                System.out.println("Enter the amount of dice to roll.");
                diceAmount = user_input.nextInt();                  // Waits for input. strings end app
                System.out.println("Enter the amount of side the dice have.");
                diceSides = user_input.nextInt();                   // Waits for input. strings end app
                System.out.println("Enter roll Modifier (+/-).");
                diceMod = user_input.nextInt();
            }
            if (diceAmount == 0 || diceSides == 0) {                //Prevents loop error with diceAmount and randomGenerator error with Sides
                System.out.println("Cannot Roll 0 dice or 0 sided dice.");
            } else {
                if (diceSides == 1) {
                    System.out.println("Rolling balls");            // Bit of fun since d1s are pointless
                }

                System.out.print("Dice roll = ");
                for (int i = 1; i <= diceAmount; ++i) {
                    Random randomGenerator = new Random();
                    int dieRoll = randomGenerator.nextInt(diceSides);
                    switch (dieRoll) {
                        case 0:                                     // Generator is between 0 and diceSides so using 0 as max
                            dieRoll = diceSides;
                            System.out.print("!" + dieRoll + "!,");
                            total += dieRoll;
                            critTotal++;
                            break;
                        case 1:
                            System.out.print("?" + dieRoll + "?,");
                            failTotal++;
                            break;
                        default:
                            System.out.print(dieRoll + ",");
                            total += dieRoll;
                            break;
                    }
                }

                System.out.println();
                System.out.println("Total Roll = " + total);
                System.out.println("Total Crits = " + critTotal);
                System.out.println("Total Fails = " + failTotal);
                int result = total + diceMod;
                System.out.println("Total Result = " + result);
            }
        }

    public static int hiddenRoll(Dice in) {
            int total = 0;

            int diceAmount = in.getDiceAmount();
            int diceSides = in.getDiceSides();                  // takes values from Dice1 object
            int diceMod = in.getDiceMod();

            if (diceAmount == 0 || diceSides == 0) {                //Prevents loop error with diceAmount and randomGenerator error with Sides
                System.out.println("Error in Dice inputs.");
            }

            for (int i = 1; i <= diceAmount; ++i) {
                Random randomGenerator = new Random();
                int dieRoll = randomGenerator.nextInt(diceSides);
                switch (dieRoll) {
                    default:
                        total += dieRoll;
                        break;
                }
            }
            total += diceMod;
            return total;
        }

    public static void battleSetup() {
    //    int roundNum = 1;                                     // to be added later
        Dice twoD10 = new Dice(2, 10, 0);

        System.out.println("Input your Name");
        Scanner user_input = new Scanner(System.in);            // Input setup
        String playerName = user_input.next();

        // Create Player
        Player player = new Player(playerName , 100 , 5 , twoD10);

        // Create Boss
        System.out.println("Battling Deathwing");
        Dice bossHP = new Dice(3, 20, 100);           //103 - 160
        Dice bossArmour = new Dice(1, 10, 4);         // 5  - 14
        Dice bossAttack = new Dice(2, 5, 8);          // 10 - 18
        Boss deathwing = new Boss("Deathwing", bossHP, bossArmour, bossAttack);

        // Declares player and boss stats
        System.out.println(playerName + " Stats:");
        System.out.println(playerName + " Health = " + player.getPlayerHealth());
        System.out.println(playerName + " Armour = " + player.getPlayerArmour());
        System.out.println(playerName + " Attack = " + player.getPlayerAttackDice());

        System.out.println("");

        System.out.println("Boss Stats:");
        System.out.println("Boss Name   = " + deathwing.getBossName());
        System.out.println("Boss Health = " + deathwing.getBossHealth());
        System.out.println("Boss Armour = " + deathwing.getBossArmour());
        System.out.println("Boss Attack = " + deathwing.getBossAttackDice());

        int bossHealth = deathwing.getBossHealth();
        int playerHealth = player.getPlayerHealth();
        String bossName = deathwing.getBossName();

        while (bossHealth > 0 && playerHealth > 0) {                // Fight sequence start

            playerTurn(player, deathwing);
            bossTurn(player, deathwing);
            playerHealth = player.getPlayerHealth();
            bossHealth = deathwing.getBossHealth();
        }

        if (playerHealth > 0 && bossHealth <= 0) {
            System.out.println("");
            System.out.println(playerName + " has defeated " + bossName);
            System.out.println("Congratulations ");
            System.out.println("Play FF7 victory theme in your head now.");
        } else if (playerHealth <= 0 && bossHealth <= 0) {
            System.out.println("");
            System.out.println("Both " + playerName + " and " + bossName + " will not be returning from this battle.");
        } else if (playerHealth <= 0 && bossHealth > 0) {
            System.out.println("");
            System.out.println(bossName + " has defeated " + playerName + " better luck next time.");
        } else {
            System.out.println(" Error in post battle calculation.");
        }
    }

    public static void playerTurn(Player player , Boss boss ){

        Dice d20 = new Dice(1,20,0);

        // PLAYERS TURN

    //    System.out.println("");                                 //round timer to be added later
    //    System.out.println("Round : " + roundNum);
    //    System.out.println("");


        // Get Player Stats
        String playerName = player.getPlayerName();
        int playerMaxHealth = player.getPlayerMaxHealth();
        int playerHealth = player.getPlayerHealth();
        int playerArmour = player.getPlayerArmour();
        Dice playerAttack = player.getPlayerAttack();

        // Get Boss Stats
        String bossName = boss.getBossName();
        int bossHealth = boss.getBossHealth();
        int bossArmour = boss.getBossArmour();

        System.out.println("");
        System.out.println("Please enter your command. (enter Help for list of inputs)");
        Scanner user_input = new Scanner(System.in);            // Input setup
        String OpCodePlayer = user_input.next();                // determines how player commits their turn

        switch (OpCodePlayer) {                                     // Help check without using turn can add to main battle switch if i learn how to return to top of loop
            // or can add more none turn using commands here
            case "Help":
                System.out.println("Current command list is:");
                System.out.println("A : roll against the bosses armour if successful dealing your damage to the boss");
                System.out.println("D : roll 1d20 to increase armour by 1 needs a roll of 11+ to succeed");
                System.out.println("P : use a healing potion restoring 3d6+5 health");
                System.out.println("Please enter a new command");
                OpCodePlayer = user_input.next();
        }

        switch (OpCodePlayer) {

            case "A":
                System.out.println(playerName + " attacks " + bossName);
                int roll = hiddenRoll(d20);
                System.out.println(playerName + " Rolls : " + roll);

                if (roll > bossArmour) {
                    int damageRoll = hiddenRoll(playerAttack);
                    System.out.println(playerName + " wounds " + bossName + " dealing " + damageRoll + " damage.");
                    bossHealth -= damageRoll;
                    System.out.println(bossName + " has " + bossHealth + " remaining.");
                } else {
                    System.out.println(playerName + " was unable to injure " + bossName + ".");
                    System.out.println(bossName + " has " + bossHealth + " remaining.");
                }
                break;

            case "D":
                System.out.println(playerName + " attempts to prepare a defense (11+ to succeed)");
                roll = hiddenRoll(d20);
                System.out.println(playerName + " rolls a " + roll);
                if (roll >= 11) {
                    System.out.println(playerName + " succeeded now has +1 armour");
                    playerArmour++;
                    System.out.println(playerName + " now has " + playerArmour + " armour");
                } else System.out.println(playerName + " was not successful");
                break;

            case "P":
                Dice potion = new Dice(3, 6, 5);
                int healing = hiddenRoll(potion);
                if (healing + playerHealth >= playerMaxHealth) {
                    healing = playerMaxHealth - playerHealth;
                }
                System.out.println(playerName + " drinks a healing potion restoring " + healing + " Health.");
                playerHealth = playerHealth + healing;
                System.out.println(playerName + " now has " + playerHealth + " Health.");
                break;

            default:
                System.out.println("Incorrect input skipping turn");
                break;
        }
        System.out.println("");
        player.setPlayerHealth(playerHealth);
        player.setPlayerArmour(playerArmour);
        player.setPlayerAttack(playerAttack);       // currently not need but may later

        boss.setBossHealth(bossHealth);
        boss.setBossArmour(bossArmour);             // currently not need but may later

    }

    public static void bossTurn(Player player , Boss boss ){

        Dice d20 = new Dice(1,20,0);

        // Get Player Stats
        String playerName = player.getPlayerName();
        int playerHealth = player.getPlayerHealth();
        int playerArmour = player.getPlayerArmour();

        // Get Boss Stats
        String bossName = boss.getBossName();
        int bossMaxHealth = boss.getBossMaxHealth();        // currently not need but may later
        int bossHealth = boss.getBossHealth();
        int bossArmour = boss.getBossArmour();
        Dice bossAttack = boss.getBossAttack();

        // BOSS TURN
        System.out.println(bossName + " attacks " + playerName + ".");        // boss attack
        int roll = hiddenRoll(d20);
        System.out.println(bossName + " Rolls : " + roll + ".");
        if (roll > playerArmour) {
            int damageRoll = hiddenRoll(bossAttack);
            System.out.println(bossName + " wounds " + playerName + " dealing " + damageRoll + " damage.");
            playerHealth -= damageRoll;
            System.out.println(playerName + " has " + playerHealth + " remaining.");
        } else {
            System.out.println(bossName + " was unable to injure " + playerName + ".");
        }

        player.setPlayerHealth(playerHealth);
        player.setPlayerArmour(playerArmour);       // currently not need but may later

        boss.setBossHealth(bossHealth);             // currently not need but may later
        boss.setBossArmour(bossArmour);             // currently not need but may later
        boss.setBossAttack(bossAttack);             // currently not need but may later

    }

    public static class Player {

        private String playerName;
        private int playerMaxHealth;
        private int playerHealth;
        private int playerArmour;
        private Dice playerAttack;
        private String playerAttackDice;

        // Constructor
        Player(String bossName, int playerMaxHealth, int playerArmour, Dice playerAttack) {
            this.playerName = bossName;
            this.playerMaxHealth = playerMaxHealth;
            this.playerHealth = this.playerMaxHealth;
            this.playerArmour = playerArmour;
            this.playerAttack = playerAttack;
            this.playerAttackDice = playerAttack.getDiceName();
        }
        // Accessors

        public String getPlayerName() {
            return playerName;
        }

        public int getPlayerMaxHealth() {
            return playerMaxHealth;
        }

        public int getPlayerHealth() {
            return playerHealth;
        }

        public int getPlayerArmour() {
            return playerArmour;
        }

        public Dice getPlayerAttack() {
            return playerAttack;
        }

        public String getPlayerAttackDice() {
            return playerAttackDice;
        }

        // Mutators (no max health since should be edited atm)

        public void setPlayerHealth(int playerHealth) {
            this.playerHealth = playerHealth;
        }

        public void setPlayerArmour(int playerArmour) {
            this.playerArmour = playerArmour;
        }

        public void setPlayerAttack(Dice playerAttack) {
            this.playerAttack = playerAttack;
        }

    }

    public static class Boss {
        private String bossName;
        private int bossMaxHealth;
        private int bossHealth;
        private int bossArmour;
        private Dice bossAttack;
        private String bossAttackDice;

        // Constructor
        Boss(String bossName, Dice bossMaxHealth, Dice bossArmour, Dice bossAttack) {
            this.bossName = bossName;
            this.bossMaxHealth = hiddenRoll(bossMaxHealth);
            this.bossHealth = this.bossMaxHealth;
            this.bossArmour = hiddenRoll(bossArmour);
            this.bossAttack = bossAttack;
            this.bossAttackDice = bossAttack.getDiceName();
        }

        // Accessors

        public String getBossName() {
            return bossName;
        }

        public int getBossMaxHealth() {
            return bossMaxHealth;
        }

        public int getBossHealth() {
            return bossHealth;
        }

        public int getBossArmour() {
            return bossArmour;
        }

        public Dice getBossAttack() {
            return bossAttack;
        }

        public String getBossAttackDice() {
            return bossAttackDice;
        }
        // Mutators

        public void setBossHealth(int bossHealth) {
            this.bossHealth = bossHealth;
        }

        public void setBossArmour(int bossArmour) {
            this.bossArmour = bossArmour;
        }

        public void setBossAttack(Dice bossAttack) {
            this.bossAttack = bossAttack;
        }

    }

    public static class Dice {
        private String diceName;
        private int diceAmount;
        private int diceSides;
        private int diceMod;

        // Constructor
        Dice(int diceAmount , int diceSides, int diceMod) {
            this.diceAmount = diceAmount;
            this.diceSides  = diceSides;
            this.diceMod = diceMod;
            this.diceName = diceAmount +"d" +diceSides+"+"+diceMod;
        }

        // Accessors

        public String getDiceName() {
            return diceName;
        }

        public int getDiceAmount() {
            return diceAmount;
        }

        public int getDiceSides() {
            return diceSides;
        }

        public int getDiceMod() {
            return diceMod;
        }

        // Mutators

        public void setDiceName(String diceName) {
            this.diceName = diceName;
        }

        public void setDiceAmount(int diceAmount) {
            this.diceAmount = diceAmount;
        }
        public void setDiceSides(int diceSides) {
            this.diceSides = diceSides;
        }
        public void setDiceMod(int diceMod) {
            this.diceMod = diceMod;
        }
    }
}
