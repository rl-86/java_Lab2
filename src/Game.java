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
        int mapY = 10;
        int mapX = 20;


        Maze maze = new Maze(mapY, mapX);
        Player p1 = new Player("Hero", 1, 1, 70, 20);
        //Monsters
        Monster m1 = new Monster("Goblin", mapY/4, mapX/5, 40, 10);
        Monster m2 = new Monster("Bat", mapY/2, mapX/3, 20, 20);
        Monster m3 = new Monster("Dragon", mapY-2, mapX-3, 100, 30);
        //Items
        Upgrade u1 = new Upgrade("a Health potion", 3, 3, 30, 0);
        maze.addItem(u1);
        Upgrade u2 = new Upgrade("a perfectly cooked Steak?", 0, 0, 15, 0);
        maze.addItem(u2);
        Upgrade u3 = new Upgrade("a Knife", 0, 0, 0, 10);
        maze.addItem(u3);
        Upgrade u4 = new Upgrade("a Shiny Sword", 0, 0, 0, 30);
        maze.addItem(u4);
        Treasure t1 = new Treasure("Golden Dragon Statue", mapX-2, mapY-2, 100);
        maze.addItem(t1);
        boolean activeGame = true;


        System.out.println("\nWelcome to The Maze");
        System.out.println("========");
        System.out.println("Enter Your name: ");
        p1.setName(sc.nextLine());
        System.out.println("Great, good luck " + p1.getName() + "\n");
        System.out.println("Use \"A\" \"W\" \"D\" \"S\" + Enter to move");


        maze.placeMonster(m1);
        maze.placeMonster(m2);
        maze.placeMonster(m3);
        maze.placeTreasure(t1);
        maze.placeItemsRandomly();

        while (activeGame) {

            maze.handleItems(p1);
            p1.playerStats();
            maze.placePlayer(p1);
            maze.moveRandom(m1);
            maze.moveRandom(m2);
            maze.moveRandom(m3);
            maze.printMaze();

            int direction = 0;



            String choice = sc.nextLine();
            switch (choice.toLowerCase()) {
                case "a":
                    direction = (p1.getX() - 1);
                    maze.updatePosition(p1, direction, p1.getY());
                    break;
                case "w":
                    direction = (p1.getY() - 1);
                    maze.updatePosition(p1, p1.getX(), direction);
                    break;
                case "d":
                    direction = (p1.getX() + 1);
                    maze.updatePosition(p1, direction, p1.getY());
                    break;
                case "s":
                    direction = (p1.getY() + 1);
                    maze.updatePosition(p1, p1.getX(), direction);
                    break;
                default:
                    System.out.println("Invalid input!\nUse \"A\", \"W\", \"D\" or \"S\" + Enter to move");
                    break;

            }

            // Monster 1 encounter
            if (p1.getY() == m1.getY() && p1.getX() == m1.getX() && Monster.isAlive(m1) == true) {
                System.out.println("\nYou have encountered a nasty looking " + m1.getName() + " with " + m1.getHealth() + " HP");
                System.out.println("Strike?");
                sc.nextLine();
                System.out.println("You strike the " + m1.getName() + " for " + p1.getStrength() + " dmg");
                System.out.println("POW!");
                m1.setHealth(m1.getHealth() - p1.getStrength());
                sc.nextLine();
                System.out.println("The " + m1.getName() + " hit you for " + m1.getStrength() + " dmg");
                System.out.println("Ouch!");
                p1.setHealth(p1.getHealth() - m1.getStrength());
                sc.nextLine();
                while (m1.getHealth() >= 1) {
                    System.out.println("The " + m1.getName() + " still has " + m1.getHealth() + "HP");
                    System.out.println("Strike again!");
                    m1.setHealth(m1.getHealth() - p1.getStrength());
                    p1.setHealth(p1.getHealth() - m1.getStrength());
                    sc.nextLine();
                }
                if (p1.getHealth() <= 0) {
                    System.out.println("Noo! The " + m1.getName() + " killed you!");
                    System.out.println(p1.getName() + " is no more...");
                    System.out.println("Game over!");
                    activeGame = false;
                }
                if (activeGame == true) {
                    System.out.println("Yes! you killed the " + m1.getName());
                    maze.removeEntity(m1);
                }

                
            }
            // Monster 2 encounter
            else if (p1.getY() == m2.getY() && p1.getX() == m2.getX() && Monster.isAlive(m2) == true) {
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
                    maze.removeEntity(m2);
                }
            }
            // Monster 3 encounter
            else if (p1.getY() == m3.getY() && p1.getX() == m3.getX() && Monster.isAlive(m3) == true) {
                System.out.println("\nYou have encountered a gigantic red " + m3.getName() + " with " + m3.getHealth() + " HP");
                System.out.println("Strike?");
                sc.nextLine();
                System.out.println("You strike the " + m3.getName() + " for " + p1.getStrength() + " dmg");
                System.out.println("POW!");
                m3.setHealth(m3.getHealth() - p1.getStrength());
                sc.nextLine();
                System.out.println("The " + m3.getName() + " hit you for " + m3.getStrength() + " dmg");
                System.out.println("Ouch!");
                p1.setHealth(p1.getHealth() - m3.getStrength());
                sc.nextLine();
                while (m3.getHealth() >= 1 && p1.getHealth() >= 1) {
                    System.out.println("The " + m3.getName() + " still has " + m3.getHealth() + "HP");
                    System.out.println("Strike again!");
                    m3.setHealth(m3.getHealth() - p1.getStrength());
                    p1.setHealth(p1.getHealth() - m3.getStrength());
                    sc.nextLine();

                }
                if (p1.getHealth() <= 0) {
                    System.out.println("Noo! The " + m3.getName() + " killed you!");
                    System.out.println(p1.getName() + " is no more...");
                    System.out.println("Game over!");
                    activeGame = false;

                }
                if (activeGame == true) {
                    System.out.println("Yes! you killed the " + m3.getName());
                    maze.removeEntity(m3);
                }
                
            } 
            // Found Treasure
            else if
            (p1.getX() == t1.getX() && p1.getY() == t1.getY()) {
                System.out.println("\nYou found the treasure!");
                System.out.println("The " + t1.getName());
                System.out.println("Congratulations! You won the game.");
                activeGame = false;

                // Encounter Monster 1
            }





        }


    }

}

