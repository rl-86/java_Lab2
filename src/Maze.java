import java.util.ArrayList;
import java.util.Random;

public class Maze {

    private int[][] map;
    private ArrayList<Item> items;

    public Maze(int y, int x) {
        this.map = new int[y][x];
        generateMaze();
        items = new ArrayList<>();
    }
    // Generate outer walls
    private void generateMaze() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (i == 0 || i == map.length - 1 || j == 0 || j == map[i].length - 1) {
                    map[i][j] = 1; // Create walls around the map
                } else {
                    map[i][j] = 0; // Empty space
                }
            }
        }
        // Generate random inner walls
        for (int i = 2; i < map.length - 2; i += 2) {
            for (int j = 2; j < map[i].length - 2; j += 2) {
                map[i][j] = 1;

                int direction = (int) (Math.random() * 4);
                switch (direction) {
                    case 0: // Up
                        map[i - 1][j] = 1;
                        break;
                    case 1: // Down
                        map[i + 1][j] = 1;
                        break;
                    case 2: // Left
                        map[i][j - 1] = 1;
                        break;
                    case 3: // Right
                        map[i][j + 1] = 1;
                        break;
                }
            }
        }

    }

    public void placePlayer(Player player) {
        map[player.getY()][player.getX()] = 3;
    }
    public void placeMonster(Monster monster) {
        map[monster.getY()][monster.getX()] = 4;
    }
    public void placeItem(Item item) {
        map[item.getY()][item.getX()] = 5;
    }
    public void placeTreasure(Treasure treasure) {
        map[treasure.getY()][treasure.getX()] = 2;
    }
    public void removeEntity(Game.Movable entity) {
        map[entity.getY()][entity.getX()] = 0;
    }

    public void placeItemsRandomly() {
        Random random = new Random();

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);

            if (item instanceof Treasure) {
                placeTreasure((Treasure) item);
            } else {
                boolean placed = false;

                while (!placed) {
                    int randomX = random.nextInt(map[0].length);
                    int randomY = random.nextInt(map.length);

                    if (map[randomY][randomX] == 0) {
                        item.setX(randomX);
                        item.setY(randomY);
                        placeItem(item);
                        placed = true;
                    }
                }
            }
        }
    }

    public void printMaze() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                char character = '0';
                switch (map[i][j]){
                    case 0:
                        character = ' '; // Empty space
                        break;
                    case 1:
                        character = '|'; // Wall
                        break;
                    case 2:
                        character = 'T'; // Treasure
                        break;
                    case 3:
                        character = 'P'; // Player
                        break;
                    case 4:
                        character = 'M'; // Monster
                        break;
                    case 5:
                        character = '*'; // Item
                        break;
                }

                System.out.print(character + " ");
            }

            System.out.println();
        }
    }

    public boolean isValidMove(int x, int y) {
        return map[y][x] != 1 && map[y][x] != 3;
    }

    public void updatePosition(Game.Movable entity, int newX, int newY) {

        if (isValidMove(newX, newY)) {
            map[entity.getY()][entity.getX()] = 0;
            entity.move(newX, newY);

            if (entity instanceof Player) {
                map[newY][newX] = 3;
            } else if (entity instanceof Monster) {
                map[newY][newX] = 4;
            }

        } else {
            if (entity instanceof Player) {
            System.out.println("There is a wall in the way!");
            }
        }

    }
    // Random movement of the monsters
    public void moveRandom(Game.Movable entity) {
        if (Monster.isAlive((Monster) entity)) {
            Random random = new Random();

            int direction = random.nextInt(4);

            int currentX = entity.getX();
            int currentY = entity.getY();

            int newX = currentX;
            int newY = currentY;

            switch (direction) {
                case 0: // Upp
                    newY = currentY - 1;
                    break;
                case 1: // Down
                    newY = currentY + 1;
                    break;
                case 2: // Left
                    newX = currentX - 1;
                    break;
                case 3: // Right
                    newX = currentX + 1;
                    break;
            }

            boolean positionOccupied = false;
            for (Item item : items) {
                if (newX == item.getX() && newY == item.getY()) {
                    positionOccupied = true;
                    break;
                }
            }

            if (!positionOccupied) {
                updatePosition(entity, newX, newY);
            }
        }
    }

    public void handleItems(Player player) {
        int playerX = player.getX();
        int playerY = player.getY();

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);

            if (item.getX() == playerX && item.getY() == playerY) {
                if (item instanceof Upgrade) {
                    Upgrade upgrade = (Upgrade) item;

                    // För att hålla reda på vad som uppdaterades
                    StringBuilder message = new StringBuilder("You found "+ upgrade.getName()+" and ");

                    // Uppdatera spelarens hälsa om hälsobonusen inte är 0
                    if (upgrade.getHealth() != 0) {
                        player.setHealth(player.getHealth() + upgrade.getHealth());
                        message.append("Your health increased by ").append(upgrade.getHealth()).append("! ");
                    }

                    // Uppdatera spelarens styrka om styrkebonusen inte är 0
                    if (upgrade.getStrength() != 0) {
                        player.setStrength(player.getStrength() + upgrade.getStrength());
                        message.append("Your strength increased by ").append(upgrade.getStrength()).append("!");
                    }

                    // Skriv endast ut meddelandet om det skedde någon uppdatering
                    if (message.length() > ("You found "+ upgrade.getName()).length()) {
                        System.out.println(message.toString());
                    }

                    // Ta bort item från kartan och listan
                    map[item.getY()][item.getX()] = 0;
                    items.remove(i);
                    break;
                }
            }
        }
    }


    public int getCell(int x, int y) {
        return map[y][x];
    }
    public ArrayList<Item> getItems() {
        return items;
    }

    public void setCell(int x, int y, int value) {
        map[y][x] = value;
    }
    public void addItem(Item item) {
        items.add(item);
    }

}