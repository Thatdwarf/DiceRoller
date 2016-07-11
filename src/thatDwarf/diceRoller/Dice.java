package thatDwarf.diceRoller;

public class Dice {
    private int DiceAmount = 0;
    private int DiceSides = 0;
    private int DiceMod = 0;


    public Dice(int diceAmount, int diceSides, int diceMod) {
        DiceAmount = diceAmount;
        DiceSides = diceSides;
        DiceMod = diceMod;
    }

    public int getDiceAmount() {
        return DiceAmount;
    }

    public int getDiceSides() {
        return DiceSides;
    }

    public int getDiceMod() {
        return DiceMod;
    }
}