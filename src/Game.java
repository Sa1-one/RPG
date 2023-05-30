import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Game {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Hero hero = null;

        try {
            System.out.print("Введите имя персонажа: ");
            String name = br.readLine();
            hero = new Hero(name, 100, 0, 0, 10, 15);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (hero != null) {
            while (true) {
                System.out.println("Куда вы хотите пойти?");
                System.out.println("1. К торговцу");
                System.out.println("2. В темный лес");
                System.out.println("3. На выход");

                try {
                    String choice = br.readLine();

                    switch (choice) {
                        case "1":
                            Merchant merchant = new Merchant();
                            merchant.interact(hero);
                            break;
                        case "2":
                            startBattle(hero);
                            break;
                        case "3":
                            System.out.println("Выход из игры.");
                            return;
                        default:
                            System.out.println("Некорректный выбор.");
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void startBattle(Hero hero) {
        Random random = new Random();
        int enemyType = random.nextInt(2);

        Character enemy;

        if (enemyType == 0) {
            enemy = new Skeleton("Скелет", 30, 10, 5, 5, 10);
        } else {
            enemy = new Goblin("Гоблин", 40, 15, 7, 3, 10);
        }

        System.out.println("Ваш противник: " + enemy.getName());

        Thread battleThread = new Thread(() -> {
            while (hero.getHealthPoints() > 0 && enemy.getHealthPoints() > 0) {
                hero.attack(enemy);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (enemy.getHealthPoints() > 0) {
                    enemy.attack(hero);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            if (hero.getHealthPoints() <= 0) {
                System.out.println("Вы погибли. Игра окончена.");
            } else {
                int experience = enemy.getExperience();
                int gold = enemy.getGold();

                hero.receiveExperience(experience);
                hero.receiveGold(gold);

                System.out.println("Вы победили! Получено " + experience + " опыта и " + gold + " золота.");
            }
        });

        battleThread.start();

        try {
            battleThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}