import java.util.Scanner;

public class Game {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Player player1 = new Player("", 0,0);

        System.out.println("\nWelcome to TheMaze");
        System.out.println("========");

        System.out.println("Enter Player 1 name: ");
        player1.setName(sc.nextLine());
        System.out.println("Great, good luck " + player1.getName().toUpperCase());
        sc.nextLine();
    }
}