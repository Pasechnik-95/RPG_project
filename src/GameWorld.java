import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class GameWorld {

    private static Scanner inputCommand;
    private static Player player;
    private static Battle battle;
    private static Merchant merchant;


    public static void main(String[] args) {
        battle = new Battle();
        inputCommand = new Scanner(new InputStreamReader(System.in));
        merchant = new Merchant();
        System.out.println("Введите ваше имя:");
        player = new Player(inputCommand.next(), 100, 10, 30, 0, 0); //создаем игрока, присваиваем в качестве имени строку из терминала
        System.out.printf("Приветствую, %s. Тебе предстоит избавить этот мир от нечисти.%n", player.getName());
        printNavigation();
        game(inputCommand.next());
    }

    private static void printNavigation() {
//        switch (currentLocation)
        System.out.println("Вы в городе. Куда вы хотите пойти?");
        System.out.println("1. К торговцу.");
        System.out.println("2. В темный лес.");
        System.out.println("3. Выйти из игры.");
        System.out.println("Введите номер и нажмите Enter:");
    }

    private static void game(String in) {
        switch (in) {
            case "1": {
                System.out.println("Здравствуй путник.");
                merchantsShop();
            }
            break;
            case "2": {
                darkForest();
            }
            break;
            case "3": {
                System.out.println("Вы уверены, что хотите выйти?");
                System.out.println("[да/нет]:");
                switch (inputCommand.next()) {
                    case "да": {
                        System.exit(1);
                    }
                    break;
                    case "нет": {
                        printNavigation();
                        game(inputCommand.next());
                    }
                    break;
                    default: {
                        System.out.print("Неверная комманда. ");
                        game("3");
                    }
                }
            }
            default:
                System.out.println("Такого места не существует.");
                printNavigation();
                game(inputCommand.next());
        }
    }

    private static void darkForest() {
        battle.fight(player, createMonster(), new FightCallback() {
            @Override
            public void fightWin() {
                System.out.printf("Теперь у вас %d опыта и %d золота, а также осталось %d едениц здоровья.%n", player.getXp(), player.getMoney(), player.getCurrentHP());
                System.out.println("Желаете продолжить поход? [да/нет]:");
                try {
                    continueCampaign(inputCommand.next());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {
                System.out.println("Game over!");
            }
        });
    }

    private static Character createMonster() {
        if ((int) (Math.random() * 10) % 2 == 0) {
            return new Goblin("Гоблин", 80, 10, 30, 10, 10);
        } else {
            return new Sceleton("Скелет", 60, 10, 30, 10, 10);
        }
    }

    private static void continueCampaign(String in) {
        switch (in) {
            case "да": {
                game("2");
            }
            break;
            case "нет": {
                printNavigation();
                game(inputCommand.next());
            }
            break;
            default: {
                System.out.print("Неверная комманда.");
                System.out.println("Желаете продолжить поход? [да/нет]:");
                continueCampaign(inputCommand.next());
            }
        }
    }

    private static void merchantsShop() {
        merchant.productCatalog();
        int newStrength = player.getStrength() + 1;
        switch(merchant.sell(player, inputCommand.next())) {
            case "potionOfHP": {
                player.setCurrentHP(player.getHp());
                System.out.println("Спасибо за покупку. Теперь ты полностью здоров!");
                System.out.println("Всего хорошего, до новых встреч!");
                printNavigation();
                game(inputCommand.next());
            }
            break;
            case "potionOfStrength": {
                player.setStrength(newStrength);
                System.out.printf("Спасибо за покупку. Твоя сила выросла на 1 единицу и составляет %d.%n", newStrength);
                System.out.println("Всего хорошего, до новых встреч!");
                printNavigation();
                game(inputCommand.next());
            }
            break;
            case "Не хватает денег": {
                System.out.println("Не хватает денег. Приходи, когда подзаработаешь.");
                System.out.println("Всего хорошего, до новых встреч!");
                printNavigation();
                game(inputCommand.next());
            }
            break;
            case "NoProduct": {
                System.out.println("Такого зелья у меня нет. Выбери из тех что есть.");
                merchantsShop();
            }
            case "Уйти": {
                System.out.println("Всего хорошего, до новых встреч!");
                printNavigation();
                game(inputCommand.next());
            }
        }
    }
}



