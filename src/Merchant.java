import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Merchant {
    public void interact(Hero hero) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("У вас " + hero.getGold() + " золота.");
        System.out.println("1. Купить зелье лечения (10 золота)");
        System.out.println("2. Вернуться в город");

        try {
            String choice = br.readLine();

            switch (choice) {
                case "1":
                    if (hero.getGold() >= 10) {
                        hero.setGold(-10);
                        System.out.println("Вы купили зелье лечения. Ваши единицы здоровья: " + hero.getHealthPoints());
                    } else {
                        System.out.println("У вас недостаточно золота.");
                    }
                    break;
                case "2":
                    System.out.println("Вы вышли из торговца.");
                    break;
                default:
                    System.out.println("Некорректный выбор.");
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}