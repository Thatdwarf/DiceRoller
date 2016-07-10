package thatDwarf.diceRoller;

import java.util.Scanner;

class DiceSetup {

    Scanner user_input = new Scanner(System.in);            // Input setup

    public int amountDice() {

        System.out.println("Enter the amount of dice to roll.");
        int diceAmount = user_input.nextInt();                  // Waits for input. strings end app
        return diceAmount;
    }

    public int facesDice() {

        System.out.println("Enter the amount of side the dice have.");
        int diceSides = user_input.nextInt();                   // Waits for input. strings end app
        return diceSides;
    }

    public int modDice() {

        System.out.println("Enter roll Modifier (+/-).");
        int diceMod = user_input.nextInt();                     // Waits for input. strings end app
        return diceMod;
    }
}