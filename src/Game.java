import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public static Scanner sc = new Scanner(System.in);

    public interface Movable {
        void move(int y, int x);
    }

    public static void main(String[] args) {

        Maze maze = new Maze(15,30);
        Player p1 = new Player("Hero", 2, 1, 100, 10);

        System.out.println("\nWelcome to The Maze");
        System.out.println("========");
/*
        System.out.println("Enter Your name: ");
        p1.setName(sc.nextLine());
        System.out.println("Great, good luck " + p1.getName() + "\n");
        System.out.println("Your stats: ");
        System.out.println("HP: " + p1.getHealth());
        System.out.println("Str: " + p1.getStrength());

        System.out.println("\nProceed into the Maze");
  */
        p1.playerStats();
        maze.printMaze();
        sc.nextLine();

    }


}
