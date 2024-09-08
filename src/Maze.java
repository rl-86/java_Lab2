public class Maze {

    private int[][] map;

    public Maze(int y, int x) {
        this.map = new int[y][x];
        generateMaze();
    }

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
    }

    public void placePlayer(Player player) {
        map[player.getY()][player.getX()] = 3;
    }
    public void placeMonster(Monster monster) {
        map[monster.getY()][monster.getX()] = 4;
    }
    public void placeTreasure(Treasure treasure) {
        map[treasure.getY()][treasure.getX()] = 2;
    }
    public void removeEntity(Game.Movable entity) {
        map[entity.getY()][entity.getX()] = 0;
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
                        character = '#'; // Wall
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
        return map[y][x] != 1;
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
            System.out.println("There is a wall in the way!");

        }

    }

    public int getCell(int x, int y) {
        return map[y][x];
    }

    public void setCell(int x, int y, int value) {
        map[y][x] = value;
    }


}
