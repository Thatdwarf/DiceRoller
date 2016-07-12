package thatDwarf.diceRoller;

import java.util.Random;

public class Roll {

    public static void diceRoll(Dice in) {
        int total = 0;
        int critTotal = 0;
        int failTotal = 0;

        int diceAmount = in.getDiceAmount();
        int diceSides = in.getDiceSides();                  // takes values from Dice1 object
        int diceMod = in.getDiceMod();

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
}


