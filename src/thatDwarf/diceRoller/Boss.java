package thatDwarf.diceRoller;

public class Boss {
        private String bossName;
        private int bossHealth;
        private int bossArmour;
        private int bossAttack;

        // Constructor
        Boss(String bossName ,int bossHealth ,int bossArmour ,int bossAttack) {
            this.bossName = bossName;
            this.bossHealth = bossHealth;
            this.bossArmour = bossArmour;
            this.bossAttack = bossAttack;
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

