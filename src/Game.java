import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public static Scanner sc = new Scanner(System.in);

    public interface Movable {
        void move(int y, int x);
    }

    public static void main(String[] args) {

        Maze maze = new Maze(5,10);
        Player p1 = new Player("Hero", 1, 1, 100, 10);
        boolean activeGame = true;

        System.out.println("\nWelcome to The Maze");
        System.out.println("========");
        System.out.println("Use A W D S + enter to move");

        /*
        System.out.println("Enter Your name: ");
        p1.setName(sc.nextLine());
        System.out.println("Great, good luck " + p1.getName() + "\n");
        System.out.println("Your stats: ");
        System.out.println("HP: " + p1.getHealth());
        System.out.println("Str: " + p1.getStrength());

        System.out.println("\nProceed into the Maze");
  */

        while (activeGame) {


            p1.playerStats();
            maze.placePlayer(p1);
            maze.printMaze();

            int direction = 0;

            String choice = sc.nextLine();
            switch(choice){
                case "a":
                    direction = (p1.getX() - 1);
                    maze.updatePosition(p1, direction,p1.getY());
                    break;
                case "w":
                    direction = (p1.getY() - 1);
                    maze.updatePosition(p1, p1.getX(),direction);
                    break;
                case "d":
                    direction = (p1.getX() + 1);
                    maze.updatePosition(p1, direction,p1.getY());
                    break;
                case "s":
                    direction = (p1.getY() + 1);
                    maze.updatePosition(p1, p1.getX(),direction);
                    break;
                case "5":
                    break;

            }

        }


    }


}
