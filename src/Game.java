import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public static Scanner sc = new Scanner(System.in);

    public interface Movable {
        void move(int y, int x);
        int getY();
        int getX();
    }

    public static void main(String[] args) {

        // Set map size
        int mapY = 15;
        int mapX = 30;


        Maze maze = new Maze(mapY, mapX);
        Player p1 = new Player("Hero", 1, 1, 21, 20);
        //Monster
        Monster m1 = new Monster("Goblin", mapY/4, mapX/5, 45, 10);
        Monster m2 = new Monster("Bat", mapY/2, mapX/3, 20, 20);
        Monster m3 = new Monster("Dragon", mapY-5, mapX-5, 100, 30);
        //Items
        Upgrade u1 = new Upgrade("Hero", mapY/4, mapX/5, 45, 10);
        Treasure t1 = new Treasure("Golden Eagle Statue", mapX-2, mapY-2, 100);
        boolean activeGame = true;

        System.out.println("\nWelcome to The Maze");
        System.out.println("========");
       /* System.out.println("Enter Your name: ");
        p1.setName(sc.nextLine());
        System.out.println("Great, good luck " + p1.getName() + "\n");
        System.out.println("Use \"A\" \"W\" \"D\" \"S\" + Enter to move");
*/
        maze.placePlayer(p1);
        maze.placeMonster(m1);
        maze.placeMonster(m2);
        maze.placeTreasure(t1);

        while (activeGame) {

            p1.playerStats();
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
                    //other moves?
                break;

            }
                //Found Treasure
            if (p1.getX() == t1.getX() && p1.getY() == t1.getY()) {
                System.out.println("\nYou found the treasure!");
                System.out.println("The " + t1.getName());
                System.out.println("Congratulations! You won the game.");
                activeGame = false;

                // Encounter Monster 1
            } else if (p1.getY() == m1.getY() && p1.getX() == m1.getX()) {
                System.out.println("\nYou have encountered a nasty looking "+m1.getName()+" with "+m1.getHealth()+" HP");
                System.out.println("Strike?");
                sc.nextLine();
                System.out.println("You strike the "+m1.getName()+" for "+p1.getStrength()+" dmg");
                System.out.println("POW!");
                m1.setHealth(m1.getHealth() - p1.getStrength());
                sc.nextLine();
                System.out.println("The "+m1.getName()+" hit you for "+m1.getStrength()+" dmg");
                System.out.println("Ouch!");
                p1.setHealth(p1.getHealth() - m1.getStrength());
                sc.nextLine();
                while (m1.getHealth() >= 1 ) {
                    System.out.println("The " + m1.getName() + " still has " + m1.getHealth() + "HP");
                    System.out.println("Strike again!");
                    m1.setHealth(m1.getHealth() - p1.getStrength());
                    p1.setHealth(p1.getHealth() - m1.getStrength());
                    sc.nextLine();
                }
                if (p1.getHealth() <= 0) {
                    System.out.println("Noo! The "+m1.getName()+" killed you!");
                    System.out.println(p1.getName()+" is no more...");
                    System.out.println("Game over...");
                    activeGame = false;
                }
                if (activeGame == true) {
                    System.out.println("Yes! you killed the " + m1.getName());
                }

                // Encounter Monster 2
            } else if (p1.getY() == m2.getY() && p1.getX() == m2.getX()) {
                System.out.println("\nYou have encountered a bloodthirsty " + m2.getName() + " with " + m2.getHealth() + " HP");
                System.out.println("Strike?");
                sc.nextLine();
                System.out.println("You strike the " + m2.getName() + " for " + p1.getStrength() + " dmg");
                System.out.println("POW!");
                m2.setHealth(m2.getHealth() - p1.getStrength());
                sc.nextLine();
                System.out.println("The " + m2.getName() + " hit you for " + m2.getStrength() + " dmg");
                System.out.println("Ouch!");
                p1.setHealth(p1.getHealth() - m2.getStrength());
                sc.nextLine();
                while (m2.getHealth() >= 1 && p1.getHealth() >= 1) {
                    System.out.println("The " + m2.getName() + " still has " + m2.getHealth() + "HP");
                    System.out.println("Strike again!");
                    m2.setHealth(m2.getHealth() - p1.getStrength());
                    p1.setHealth(p1.getHealth() - m2.getStrength());
                    sc.nextLine();

                }
                if (p1.getHealth() <= 0) {
                    System.out.println("Noo! The " + m2.getName() + " killed you!");
                    System.out.println(p1.getName() + " is no more...");
                    System.out.println("Game over!");
                    activeGame = false;

                }
                if (activeGame == true) {
                    System.out.println("Yes! you killed the " + m2.getName());
                }
            }

        }


    }

}

