public class Battle {
    boolean isAlive;

    public void fight(Character player, Character enemy, FightCallback fightCallback) {
        Runnable runnable = () -> {
            boolean isAlive = false;
            int step = 1;
            System.out.printf("На вас напал %s. Завязался бой.%n", enemy.getName());
            while (!isAlive) {
                System.out.println("Ход " + step + ":");
                if (step % 2 == 0) {
                    isAlive = hit(enemy, player, fightCallback);
                } else {
                    isAlive = hit(player, enemy, fightCallback);
                }
                step++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }

    private Boolean hit(Character attacker, Character defender, FightCallback fightCallback) {

        int i = attacker.attack(defender);

        if (i != 0) {
            defender.setCurrentHP(defender.getCurrentHP() - i);
            System.out.printf("%s атаковал %s и нанес урон -%d. [%d/%d]%n", attacker.getName(), defender.getName(), i, defender.getCurrentHP(), defender.getHp());
        } else {
            System.out.printf("%s атаковал, но %s увернулся.%n", attacker.getName(), defender.getName());
        }

        if (defender.getCurrentHP() <= 0 && defender instanceof Player) {
            System.out.println("Вы отважно сражались, но пали в бою!");
            fightCallback.fightLost();
            return true;
        } else if (defender.getCurrentHP() <= 0) {
            System.out.printf("%s повержен! Вы получили %d опыта и %d золота.%n", defender.getName(), defender.getXp(), defender.getMoney());
            attacker.setXp(attacker.getXp() + defender.getXp());
            attacker.setMoney(attacker.getMoney() + defender.getMoney());
            fightCallback.fightWin();
            return true;
        } else {
            return false;
        }
    }
}

/* Удар:
если удар прошел, т.е. attack вернул число, уменьшить жизнь на урон, вернуть строку, что нанесен урон
если удар не прошел, т.е. attack вернул 0, то просто вернуть строку, что увернулся.

если хп защищающегося ниже 0, то

 */