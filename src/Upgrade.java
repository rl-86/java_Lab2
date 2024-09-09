public class Upgrade extends Item {

    private int health;
    private int strength;

    public Upgrade(String name, int x, int y, int health, int strength) {
        super(name, x, y);
        this.health = health;
        this.strength = strength;
    }
    public int getHealth() {
        return health;
    }
    public int getStrength() {
        return strength;
    }

}
