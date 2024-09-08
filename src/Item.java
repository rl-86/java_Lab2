public class Item implements Game.Movable {
    private String name;
    private int x;
    private int y;

    public Item(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    // Getters
    public String getName() {
        return name;
    }
    public int getX() {
        return x;
    }

    @Override
    public void move(int y, int x) {

    }

    public int getY() {
        return y;
    }


}