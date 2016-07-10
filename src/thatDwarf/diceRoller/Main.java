package thatDwarf.diceRoller;

import java.util.Scanner;
import java.util.Random;

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

public class Main extends DiceSetup {

    public static void main(String[] args) {

        int total = 0;
        int critTotal = 0;
        int failTotal = 0;

        DiceSetup diceRequest = new DiceSetup();
        int diceAmount = diceRequest.amountDice();
        int diceSides = diceRequest.facesDice();
        int diceMod = diceRequest.modDice();


/*        Scanner user_input = new Scanner(System.in);            // Input setup

        System.out.println("Enter the amount of dice to roll.");
        int diceAmount = user_input.nextInt();                  // Waits for input. strings end app

        System.out.println("Enter the amount of side the dice have.");
        int diceSides = user_input.nextInt();                   // Waits for input. strings end app

        System.out.println("Enter roll Modifier (+/-).");
        int diceMod = user_input.nextInt();                     // Waits for input. strings end app
*/
        if (diceAmount == 0 || diceSides == 0) {                //Prevents loop error with diceAmount and randomGenerator error with Sides
            System.out.println("Cannot Roll 0 dice or 0 sided dice.");
        }
        else {
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
}