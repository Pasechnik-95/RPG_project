abstract class Character implements Fightable, Killable { //класс персонажей
    private String name;
    private int hp, strength, agility, xp, money; //статы

    private int currentHP; //хранит текущее значение здоровья

    public Character(String name, int hp, int strength, int agility) { // конструктор для NPC
        this.name = name;
        this.hp = hp;
        this.strength = strength;
        this.agility = agility;
        this.currentHP = hp;
    }

    public Character(String name, int hp, int strength, int agility, int xp, int money) { // конструктор для игрока
        this.name = name;
        this.hp = hp;
        this.strength = strength;
        this.agility = agility;
        this.xp = xp;
        this.money = money;
        this.currentHP = hp;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getAgility() {
        return agility;
    }

    public int getStrength() {
        return strength;
    }

    public int getMoney() {
        return money;
    }

    public int getXp() {
        return xp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public int attack(Character enemy) {
        return enemy.damage(this.getStrength());
    }

    @Override
    public int damage(int damage) {
        if (this.getAgility() > Math.random() * 100) {
            return 0;
        } else {
            return damage;
        }
    }
}
