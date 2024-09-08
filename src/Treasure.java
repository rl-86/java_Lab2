public class Treasure extends Item {
    private int value;

    public Treasure(String name, int x, int y, int value) {
        super(name, x, y);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
