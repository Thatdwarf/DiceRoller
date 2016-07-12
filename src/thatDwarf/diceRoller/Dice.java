package thatDwarf.diceRoller;

import java.util.Scanner;

public class Dice {
    private int diceAmount;
    private int diceSides;
    private int diceMod;

    Scanner user_input = new Scanner(System.in);            // Input setup


    // Constructor
    Dice() {
        System.out.println("Enter the amount of dice to roll.");
        diceAmount = user_input.nextInt();                  // Waits for input. strings end app
        System.out.println("Enter the amount of side the dice have.");
        diceSides = user_input.nextInt();                   // Waits for input. strings end app
        System.out.println("Enter roll Modifier (+/-).");
        diceMod = user_input.nextInt();                     // Waits for input. strings end app
    }

    // Accessors
    public int getDiceAmount() {
        return diceAmount;
    }

    public int getDiceSides() {
        return diceSides;
    }

    public int getDiceMod() {
        return diceMod;
    }
}