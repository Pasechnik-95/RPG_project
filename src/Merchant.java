
public class Merchant implements Seller{


    private String[] goods = {"Зелье здоровья", "Зелье силы"};
    private int[] price = {10, 50};


    private enum Goods {
        POTION_OF_HEALTH,
        POTION_OF_AGILITY,

    }

    @Override
    public String sell(Character player, String product) {
        switch (product) {
            case "1": {
                if (player.getMoney() < price[0]) {
                    return "Не хватает денег";
                } else {
                    return "potionOfHP";
                }
            }
            case "2": {
                if (player.getMoney() < price[1]) {
                    return "Не хватает денег";
                } else {
                    return "potionOfStrength";
                }
            }
            case "нет": {
                return "Уйти";
            }
            default: {
                return "NoProduct";
            }
        }
    }

    public void productCatalog() {
        System.out.println("В продаже у меня есть следующие зелья:");
        for (int i = 0; i < goods.length; i++) {
            System.out.printf("%d. %s. Стоит %d золота.%n", i + 1, goods[i], price[i]);
        }
        System.out.println("Какое хочешь приобрести?");
        System.out.println("Введите номер и нажмите Enter. Или введите [нет], чтобы уйти.");
    }

}
