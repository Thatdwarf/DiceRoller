package thatDwarf.diceRoller;

import java.util.Scanner;

public class Main extends Rolls {

    public static void main(String[] args) {

        System.out.println("Do you wish to roll or battle");
        System.out.println("To roll enter Roll, To battle enter Battle");
        Scanner user_input = new Scanner(System.in);            // Input setup
        String rollOrBattle = user_input.next();

        switch (rollOrBattle) {
            case "Roll":
                Dice Dice1 = new Dice(0, 0, 0);                // create new dice object
                diceRoll(Dice1);                        // perform diceRoll method on created Dice
                break;

            case "Battle":
                System.out.println("Battling Deathwing");
                Dice bossHP = new Dice(3,20,100);           //103 - 160
                Dice bossArmour = new Dice(1,10,4);         // 5  - 14
                Dice bossAttack = new Dice(2,5,8);          // 10 - 18
                Boss deathwing = new Boss("Deathwing", bossHP, bossArmour, bossAttack);
                battle(deathwing);
                break;

            default:
                System.out.println("Error Incorrect Input");
        }


    }

    public static void battle(Boss in) {

        System.out.println("Input your Name");
        Scanner user_input = new Scanner(System.in);            // Input setup
        String playerName = user_input.next();
        int playerMaxHealth = 100;
        int playerHealth = playerMaxHealth;
        int playerArmour = 5;
        Dice playerAttack = new Dice(2,10,0);
        int roundNum = 1;
        int roll;

        String bossName = in.getBossName();
        int bossHealth = in.getBossHealth();
        int bossArmour = in.getBossArmour();
        int bossAttack = in.getBossAttack();

        System.out.println(playerName +" Stats:");
        System.out.println(playerName +" Health = " + playerHealth);
        System.out.println(playerName +" Armour = " + playerArmour);
        System.out.println(playerName +" Attack = 2d10");

        System.out.println("Boss Stats:");
        System.out.println("Boss Name   = " + bossName);
        System.out.println("Boss Health = " + bossHealth);
        System.out.println("Boss Armour = " + bossArmour);
        System.out.println("Boss Attack = " + bossAttack);

        Dice d20 = new Dice(1, 20, 0);

        while (bossHealth > 0 && playerHealth > 0) {                // Fight sequence start

            System.out.println("");
            System.out.println("Round : " + roundNum);
            System.out.println("");

            // PLAYERS TURN
            System.out.println("Please enter your command. (enter Help for list of inputs)");
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
                    roll = hiddenRoll(d20);
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
                    System.out.println(playerName + " rolls a " + roll );
                    if (roll >= 11){
                        System.out.println(playerName + " succeeded now has +1 armour");
                        playerArmour++;
                        System.out.println(playerName + " now has " + playerArmour + " armour");
                    }
                    else System.out.println(playerName + " was not successful");
                    break;

                case "P":
                    Dice potion = new Dice(3,6,5);
                    int healing = hiddenRoll(potion);
                    if (healing + playerHealth >= playerMaxHealth){
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

            // BOSS TURN
            System.out.println(bossName + " attacks " + playerName + ".");        // boss attack
            roll = hiddenRoll(d20);
            System.out.println(bossName + " Rolls : " + roll +".");
            if (roll > playerArmour) {
                System.out.println(bossName + " wounds " + playerName + " dealing " + bossAttack + " damage.");
                playerHealth -= bossAttack;
                System.out.println(playerName + " has " + playerHealth + " remaining.");
            }
            else {
                System.out.println(bossName + " was unable to injure " + playerName + ".");
            }
            roundNum++;
        }

        if (playerHealth > 0 && bossHealth <= 0){
            System.out.println("");
            System.out.println(playerName + " has defeated " + bossName);
            System.out.println("Congratulations ");
            System.out.println("Play FF7 victory theme in your head now.");
        }
        else if (playerHealth <= 0 && bossHealth <=0) {
            System.out.println("");
            System.out.println("Both " + playerName + " and " + bossName + " will not be returning from this battle.");
        }
        else if (playerHealth <= 0 && bossHealth > 0) {
            System.out.println("");
            System.out.println(bossName + " has defeated " + playerName + " better luck next time.");
        }
        else{
            System.out.println(" Error in post battle calculation.");
        }
    }
}