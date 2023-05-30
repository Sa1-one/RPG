import java.util.Random;

abstract class Character {
    private String name;
    private int healthPoints;
    private int experience;
    private int gold;
    private int strength;
    private int agility;

    public Character(String name, int healthPoints, int experience, int gold, int strength, int agility) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.experience = experience;
        this.gold = gold;
        this.strength = strength;
        this.agility = agility;
    }

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold += gold;
    }

    public int getExperience() {
        return experience;
    }

    public int setExperience(int experience){
        return this.experience += experience;
    }

    public void attack(Character enemy) {
        Random random = new Random();
        int hitChance = agility * 3;
        int attackPower = strength;

        if (random.nextInt(100) <= hitChance) {
            enemy.takeDamage(attackPower);
            System.out.println(name + " атаковал " + enemy.getName() + " и нанес " + attackPower + " урона.");
        } else {
            System.out.println(name + " промахнулся.");
        }
    }

    public void takeDamage(int damage) {
        healthPoints -= damage;
        if (healthPoints < 0) {
            healthPoints = 0;
        }
    }
}