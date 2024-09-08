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
        // Position for the treasure
        //map[13][28] = 2;
        // Starting position for the player
        //map[1][1] = 3;
    }

    public void placePlayer(Player player) {
        map[player.getY()][player.getX()] = 3;

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
        if (entity instanceof Player) {
            Player player = (Player) entity;
            map[player.getY()][player.getX()] = 0;

            if (isValidMove(newX, newY)) {
                player.move(newX, newY);
                map[newY][newX] = 3;
            } else {
                System.out.println("Cannot move there!");
            }
        }

    }


    public int getCell(int x, int y) {
        return map[y][x];
    }

    public void setCell(int x, int y, int value) {
        map[y][x] = value;
    }


}
