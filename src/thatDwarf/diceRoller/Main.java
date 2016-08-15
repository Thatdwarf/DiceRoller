package thatDwarf.diceRoller;

import java.util.Random;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) {

        // Decide to roll or battle
        System.out.println("Do you wish to roll or battle");
        System.out.println("To roll enter Roll, To battle enter Battle");
        Scanner user_input = new Scanner(System.in);            // Input setup
        String rollOrBattle = user_input.next();

        switch (rollOrBattle) {
            case "Roll":
                boolean repeat = true;

                Dice Dice1 = new Dice(0,0,0);                // create new dice object with settings of 0 0 0
                while (repeat == true) {
                    diceRoll(Dice1);                            // perform diceRoll method on created Dice
                    System.out.println("Do you want to roll again? (Y/N)");
                    String repeatRequest = user_input.next();
                    switch (repeatRequest) {
                        case "Y":
                            Dice1.setDiceAmount(0);
                            Dice1.setDiceSides(0);
                            Dice1.setDiceMod(0);
                            break;
                        case "N":
                            repeat = false;
                            break;
                    }
                }

                break;

            case "Battle":
                battleSetup();                              // go to battle setup method
                break;

            default:
                System.out.println("Error Incorrect Input"); // error message for incorrect inputs (TO ADD : re-enter method start/ re allow input)
        }


    }

    public static void diceRoll(Dice in) {
            int total = 0;
            int critTotal = 0;                                                      // declare variables used near for totals
            int failTotal = 0;

            int diceAmount = in.getDiceAmount();
            int diceSides = in.getDiceSides();                                      // gets values from dice object
            int diceMod = in.getDiceMod();

            // If all dice values are 0 allow for custom inputs
            if (diceAmount == 0 && diceSides == 0 && diceMod == 0) {

                Scanner user_input = new Scanner(System.in);                        // Input setup

                System.out.println("Enter the amount of dice to roll.");
                diceAmount = user_input.nextInt();                                  // Waits for input. strings end app
                System.out.println("Enter the amount of side the dice have.");
                diceSides = user_input.nextInt();                                   // Waits for input. strings end app
                System.out.println("Enter roll Modifier (+/-).");
                diceMod = user_input.nextInt();

                in.setDiceAmount(diceAmount);
                in.setDiceSides(diceSides);                                         // sets inputs as values for the object (Currently unused)
                in.setDiceMod(diceMod);
            }

            // checks for 0 in amount/sides
            if (diceAmount == 0 || diceSides == 0) {                                //Prevents loop error with diceAmount and randomGenerator error with Sides
                System.out.println("Cannot Roll 0 dice or 0 sided dice.");
            }
            // if passed go on to rolls
            else {
                if (diceSides == 1) {
                    System.out.println("Rolling balls");                            // Bit of fun since d1s are pointless
                }

                System.out.println("Rolling : " + in.getDiceName());
                System.out.print("Dice roll = ");
                for (int i = 1; i <= diceAmount; ++i) {                             // do i roll for each diceAmount
                    Random randomGenerator = new Random();
                    int dieRoll = randomGenerator.nextInt(diceSides);               // random gen 0 to (diceSides - 1)
                    switch (dieRoll) {
                        //max check
                        case 0:                                                     // Generator is between 0 and diceSides so using 0 as max
                            dieRoll = diceSides;
                            System.out.print("!" + dieRoll + "!,");
                            total += dieRoll;
                            critTotal++;
                            break;
                        // min check
                        case 1:
                            System.out.print("?" + dieRoll + "?,");
                            failTotal++;
                            break;
                        //all other results
                        default:
                            System.out.print(dieRoll + ",");
                            total += dieRoll;
                            break;
                    }
                }

                System.out.println();
                System.out.println("Total Roll = " + total);
                System.out.println("Total Crits = " + critTotal);                   // print results of rolls
                System.out.println("Total Fails = " + failTotal);
                int result = total + diceMod;                                       // adds mod
                System.out.println("Total Result = " + result);                     // print total
            }
        }

    public static int hiddenRoll(Dice in) {

            // for roll where only total is wanted
            int total = 0;

            int diceAmount = in.getDiceAmount();
            int diceSides = in.getDiceSides();                                      // takes values from Dice object
            int diceMod = in.getDiceMod();

            if (diceAmount == 0 || diceSides == 0) {                                //Prevents loop error with diceAmount and randomGenerator error with Sides
                System.out.println("Error in Dice inputs.");
            }

            for (int i = 1; i <= diceAmount; ++i) {
                Random randomGenerator = new Random();                              // simpler dice roller without crit/fail checks or print statements
                int dieRoll = randomGenerator.nextInt(diceSides);
                switch (dieRoll) {
                    default:
                        total += dieRoll;
                        break;
                }
            }
            total += diceMod;
            return total;                                                           //returns value instead of prints like diceRoll
        }

    public static void battleSetup() {
    //    int roundNum = 1;                                                         // to be added later
        Dice twoD10 = new Dice(2, 10, 0);

        // character creation
        System.out.println("Input your Name");
        Scanner user_input = new Scanner(System.in);                                // Input setup
        String playerName = user_input.next();

        Player player = new Player(playerName , 100 , 5 , twoD10);                  // create player object

        // Create Boss
        System.out.println("Battling Deathwing");
        Dice bossHP = new Dice(3, 20, 100);           //103 - 160
        Dice bossArmour = new Dice(1, 10, 4);         // 5  - 14                    // boss uses dice for stats to randomise
        Dice bossAttack = new Dice(2, 5, 8);          // 10 - 18                    // the battle to an extent
        Boss deathwing = new Boss("Deathwing", bossHP, bossArmour, bossAttack);

        // Declares player and boss stats
        System.out.println(playerName + " Stats:");
        System.out.println(playerName + " Health = " + player.getPlayerHealth());
        System.out.println(playerName + " Armour = " + player.getPlayerArmour());
        System.out.println(playerName + " Attack = " + player.getPlayerAttackDice());

        System.out.println(""); // small gap for structure

        System.out.println("Boss Stats:");
        System.out.println("Boss Name   = " + deathwing.getBossName());
        System.out.println("Boss Health = " + deathwing.getBossHealth());
        System.out.println("Boss Armour = " + deathwing.getBossArmour());
        System.out.println("Boss Attack = " + deathwing.getBossAttackDice());

        int bossHealth = deathwing.getBossHealth();
        int playerHealth = player.getPlayerHealth();                                // gets HPs and boss name (player name declared during creation)
        String bossName = deathwing.getBossName();                                  // other stats only used in combat loop

        while (bossHealth > 0 && playerHealth > 0) {                                // Fight sequence until 1 or more HP < 0

            playerTurn(player, deathwing);                                          // player combat phase
            bossTurn(player, deathwing);                                            // boss combat phase
            playerHealth = player.getPlayerHealth();
            bossHealth = deathwing.getBossHealth();                                 // gets new HP values from objects
        }

        // Victory/Defeat/Draw declaration
        if (playerHealth > 0 && bossHealth <= 0) {                                  // player alive boss dead (Victory)
            System.out.println("");
            System.out.println(playerName + " has defeated " + bossName);
            System.out.println("Congratulations ");
            System.out.println("Play FF7 victory theme in your head now.");
        } else if (playerHealth <= 0 && bossHealth <= 0) {                          // both dead (Draw)
            System.out.println("");
            System.out.println("Both " + playerName + " and " + bossName + " will not be returning from this battle.");
        } else if (playerHealth <= 0 && bossHealth > 0) {                           // player dead boss alive (defeat)
            System.out.println("");
            System.out.println(bossName + " has defeated " + playerName + " better luck next time.");
        } else {                                                                    // something else (not seen used yet)
            System.out.println(" Error in post battle calculation.");
        }
    }

    public static void playerTurn(Player player , Boss boss ){

        Dice d20 = new Dice(1,20,0);                                                // declares a d20 (used in standard rolls)

        // PLAYERS TURN

    //    System.out.println("");                                                   //round timer to be added later
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
        Scanner user_input = new Scanner(System.in);                            // Input setup
        String OpCodePlayer = user_input.next();                                // determines how player commits their turn

        switch (OpCodePlayer) {                                                 // Help check without using turn can add to main battle switch if i learn how to return to top of loop  (? add playerTurn() to help in main to do this?)
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

            // player attacks
            case "A":
                System.out.println(playerName + " attacks " + bossName);
                int roll = hiddenRoll(d20);                                     // attack rolls
                System.out.println(playerName + " Rolls : " + roll);

                // checks for "hit"
                if (roll > bossArmour) {
                    int damageRoll = hiddenRoll(playerAttack);
                    System.out.println(playerName + " wounds " + bossName + " dealing " + damageRoll + " damage.");
                    bossHealth -= damageRoll;
                    System.out.println(bossName + " has " + bossHealth + " remaining.");
                }

                // hit fails
                else {
                    System.out.println(playerName + " was unable to injure " + bossName + ".");
                    System.out.println(bossName + " has " + bossHealth + " remaining.");
                }
                break;

            // player defends
            case "D":
                System.out.println(playerName + " attempts to prepare a defense (11+ to succeed)");
                roll = hiddenRoll(d20);                                         // roll to succeed
                System.out.println(playerName + " rolls a " + roll);

                //Succeeds
                if (roll >= 11) {
                    System.out.println(playerName + " succeeded now has +1 armour");
                    playerArmour++;
                    System.out.println(playerName + " now has " + playerArmour + " armour");
                }
                //failed
                else System.out.println(playerName + " was not successful");
                break;

            //player heals
            case "P":
                Dice potion = new Dice(3, 6, 5);                                // declare potions dice values
                int healing = hiddenRoll(potion);                               // Heal depends on dice roll
                if (healing + playerHealth >= playerMaxHealth) {                // check for healing above starting/max health
                    healing = playerMaxHealth - playerHealth;                   // prevents over healing
                }
                System.out.println(playerName + " drinks a healing potion restoring " + healing + " Health.");
                playerHealth = playerHealth + healing;                          // applies healing
                System.out.println(playerName + " now has " + playerHealth + " Health.");
                break;
            // other inputs (to try adding playerTurn method here to prevent skipping
            default:
                System.out.println("Incorrect input skipping turn");
                break;
        }
        System.out.println("");                                                 //structure spacing

        //Sets new values for player and boss HP + more
        player.setPlayerHealth(playerHealth);
        player.setPlayerArmour(playerArmour);
        player.setPlayerAttack(playerAttack);                                   // currently not need but may later

        boss.setBossHealth(bossHealth);
        boss.setBossArmour(bossArmour);                                         // currently not need but may later

    }

    public static void bossTurn(Player player , Boss boss ){

        Dice d20 = new Dice(1,20,0);                                                // declares a d20 (used in standard rolls)

        // Get Player Stats from object
        String playerName = player.getPlayerName();
        int playerHealth = player.getPlayerHealth();
        int playerArmour = player.getPlayerArmour();

        // Get Boss Stats from object
        String bossName = boss.getBossName();
        int bossMaxHealth = boss.getBossMaxHealth();        // currently not need but may later
        int bossHealth = boss.getBossHealth();
        int bossArmour = boss.getBossArmour();
        Dice bossAttack = boss.getBossAttack();

        // BOSS TURN
        // Boss Attacks
        System.out.println(bossName + " attacks " + playerName + ".");
        int roll = hiddenRoll(d20);                                                 //attack roll
        System.out.println(bossName + " Rolls : " + roll + ".");
        //Success
        if (roll > playerArmour) {
            int damageRoll = hiddenRoll(bossAttack);
            System.out.println(bossName + " wounds " + playerName + " dealing " + damageRoll + " damage.");
            playerHealth -= damageRoll;
            System.out.println(playerName + " has " + playerHealth + " remaining.");
        }
        //Failed
        else {
            System.out.println(bossName + " was unable to injure " + playerName + ".");
        }

        // Sets new player and Boss values
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

            // Sets values to inputs and creates max health as the initial starting health
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

        // Mutators (no max health/name since should be edited atm)

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
            // Sets values to inputs and creates max health as the initial starting health
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
            // Sets values to inputs and creates a name using standard dice conventions (eg. 2d6+1 = 2 6 sided dice roll + 1)
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

        public void setDiceAmount(int diceAmount) {
            this.diceAmount = diceAmount;
            setDiceName();
        }
        public void setDiceSides(int diceSides) {
            this.diceSides = diceSides;
            setDiceName();
        }
        public void setDiceMod(int diceMod) {
            this.diceMod = diceMod;
            setDiceName();
        }

        public void setDiceName() {         // changes name of dice (added to all Mutators
            this.diceName = getDiceAmount()+"d" +getDiceSides()+"+"+getDiceMod();
        }
    }
}
