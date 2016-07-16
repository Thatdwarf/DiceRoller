package thatDwarf.diceRoller;

public class Boss extends Rolls {
        private String bossName;
        private int bossHealth;
        private int bossArmour;
        private int bossAttack;

        // Constructor
        Boss(String bossName ,Dice bossHealth ,Dice bossArmour ,Dice bossAttack ) {
            this.bossName = bossName;
            this.bossHealth = hiddenRoll(bossHealth);
            this.bossArmour = hiddenRoll(bossArmour);
            this.bossAttack = hiddenRoll(bossAttack);
        }

        // Accessors

        public String getBossName(){ return bossName; }
        public int getBossHealth() {
            return bossHealth;
        }
        public int getBossArmour() {
            return bossArmour;
        }
        public int getBossAttack() {
            return bossAttack;
        }


        // Mutators

        public void setBossName(String bossName){
            this.bossName = bossName;
        }
        public void setBossHealth(int bossHealth){ this.bossHealth = bossHealth; }
        public void setBossArmour(int bossArmour){
            this.bossArmour = bossArmour;
        }
        public void setBossAttack(int bossAttack){
            this.bossAttack = bossAttack;
        }
    }

