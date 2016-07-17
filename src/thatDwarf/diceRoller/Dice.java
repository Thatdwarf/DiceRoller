package thatDwarf.diceRoller;

public class Dice {
    private int diceAmount;
    private int diceSides;
    private int diceMod;

    // Constructor
    Dice(int diceAmount , int diceSides, int diceMod) {
            this.diceAmount = diceAmount;
            this.diceSides  = diceSides;
            this.diceMod = diceMod;
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

    // Mutators
    public void setDiceAmount(int diceAmount) {
        this.diceAmount = diceAmount;
    }
    public void setDiceSides(int diceSides) {
        this.diceSides = diceSides;
    }
    public void setDiceMod(int diceMod) {
        this.diceMod = diceMod;
    }
}