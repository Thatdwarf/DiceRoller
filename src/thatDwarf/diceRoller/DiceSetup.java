package thatDwarf.diceRoller;

import java.util.Scanner;

class DiceSetup {
    int diceAmount;
    int diceSides;
    int diceMod;

    Scanner user_input = new Scanner(System.in);            // Input setup

    DiceSetup() {
        System.out.println("Enter the amount of dice to roll.");
        diceAmount  = user_input.nextInt();                  // Waits for input. strings end app
        System.out.println("Enter the amount of side the dice have.");
        diceSides = user_input.nextInt();                   // Waits for input. strings end app
        System.out.println("Enter roll Modifier (+/-).");
        diceMod = user_input.nextInt();                     // Waits for input. strings end app
    }
//
//    public int amountDice() {
//
//        System.out.println("Enter the amount of dice to roll.");
//        int diceAmount = user_input.nextInt();                  // Waits for input. strings end app
//        return diceAmount;
//    }
//
//    public int facesDice() {
//
//        System.out.println("Enter the amount of side the dice have.");
//        int diceSides = user_input.nextInt();                   // Waits for input. strings end app
//        return diceSides;
//    }
//
//    public int modDice() {
//
//        System.out.println("Enter roll Modifier (+/-).");
//        int diceMod = user_input.nextInt();                     // Waits for input. strings end app
//        return diceMod;
//    }
}