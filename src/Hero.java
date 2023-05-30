class Hero extends Character {
    public Hero(String name, int healthPoints, int experience, int gold, int strength, int agility) {
        super(name, healthPoints, experience, gold, strength, agility);
    }

    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if (getHealthPoints() <= 0) {
            System.out.println(getName() + " погиб. Игра окончена.");
        }
    }

    public void receiveExperience(int experience){
        setExperience(experience);
    }

    public void receiveGold(int gold){
        setGold(gold);
    }
}