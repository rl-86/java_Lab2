import java.util.ArrayList;

public class Player implements Game.Movable {

    private String name;
    private int y;
    private int x;
    private int health;
    private int strength;
    private ArrayList<Item> items;

    public Player(String name, int y, int x, int health, int strength) {
        this.name = name;
        this.y = y;
        this.x = x;
        this.health = health;
        this.strength = strength;
        this.items = new ArrayList<>();
    }

    // Display player stats
    public void playerStats() {
            System.out.println("\nPlayer: "+name+"\t HP: "+health+"\t Str: "+strength+"\n pos: x"+getX()+" y"+getY());
    }

    @Override
    public void move(int newX, int newY) {
        this.x = newX;
        this.y = newY;
        System.out.println(name + " moved to position: " + newX + ", " + newY);
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public void addItem(Item item) {
        items.add(item);
    }

    //Getters
    public String getName() {
        return name;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getHealth() {
        return health;
    }
    public int getStrength() {
        return strength;
    }
    public ArrayList<Item> getItems() {
        return items;
    }
}
