package thatDwarf.diceRoller;

import java.util.Scanner;

public class Main extends Rolls {

    public static void main(String[] args) {

        String rollOrBattle;

        System.out.println("Do you wish to roll or battle");
        System.out.println("To roll enter Roll, To battle enter Battle");
        Scanner user_input = new Scanner(System.in);            // Input setup
        rollOrBattle = user_input.next();

        switch (rollOrBattle) {
            case "Roll":
                Dice Dice1 = new Dice(0, 0, 0);                // create new dice object
                diceRoll(Dice1);                        // perform diceRoll method on created Dice
                break;

            case "Battle":
                System.out.println("Battling Deathwing");
                Boss deathwing = new Boss("Deathwing", 100, 5, 10);
                battle(deathwing);
                break;

            default:
                System.out.println("Error Incorrect Input");
        }


    }

    public static void battle(Boss in) {

        int playerHealth = 100;
        int playerArmour = 5;
        int playerAttack = 10;
        int roundNum = 1;
        int roll;

        String bossName = in.getBossName();
        int bossHealth = in.getBossHealth();
        int bossArmour = in.getBossArmour();
        int bossAttack = in.getBossAttack();

        System.out.println("Player Stats:");
        System.out.println("Player Health = " + playerHealth);
        System.out.println("Player Armour = " + playerArmour);
        System.out.println("Player Attack = " + playerAttack);

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

            System.out.println("Player attacks " + bossName);       // player attack
            roll = hiddenRoll(d20);
            System.out.println("Player Rolls : " + roll);
            if (roll > bossArmour) {
                System.out.println("Player wounds " + bossName + " dealing " + playerAttack + " damage.");
                bossHealth -= playerAttack;
                System.out.println(bossName + "has " + bossHealth + " remaining.");
            }
            else {
                System.out.println("Player was unable to injure " + bossName +".");
            }

            System.out.println(bossName + " attacks Player");        // boss attack
            roll = hiddenRoll(d20);
            System.out.println(bossName + " Rolls : " + roll +".");
            if (roll > playerArmour) {
                System.out.println(bossName + " wounds Player dealing " + bossAttack + " damage.");
                playerHealth -= bossAttack;
                System.out.println("Player has " + playerHealth + " remaining.");
            }
            else {
                System.out.println(bossName + " was unable to injure Player.");
            }
            roundNum++;
        }

        if (playerHealth > 0 && bossHealth <= 0){
            System.out.println("");
            System.out.println("Player has defeated " + bossName);
            System.out.println("Congratulations ");
            System.out.println("Play FF7 victory theme in your head now.");
        }
        else if (playerHealth <= 0 && bossHealth <=0) {
            System.out.println("");
            System.out.println("Both Player and " + bossName + " will not be returning from this battle.");
        }
        else if (playerHealth <= 0 && bossHealth > 0) {
            System.out.println("");
            System.out.println(bossName + " has defeated the Player better luck next time.");
        }
        else{
            System.out.println(" Error in post battle calculation.");
        }
    }
}