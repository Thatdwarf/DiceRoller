package thatDwarf.diceRoller;

import java.util.Scanner;

public class DiceSetup {
    private int diceAmount;
    private int diceSides;
    private int diceMod;

    Scanner user_input = new Scanner(System.in);            // Input setup

    DiceSetup() {
        System.out.println("Enter the amount of dice to roll.");
        diceAmount = user_input.nextInt();                  // Waits for input. strings end app
        System.out.println("Enter the amount of side the dice have.");
        diceSides = user_input.nextInt();                   // Waits for input. strings end app
        System.out.println("Enter roll Modifier (+/-).");
        diceMod = user_input.nextInt();                     // Waits for input. strings end app

        Dice Dice1 = new Dice(diceAmount, diceSides, diceMod);
    }
}