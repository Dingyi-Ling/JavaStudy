package JavaProgramming.ConditionalsAndMethods.NimGame;

import java.util.Scanner;

public class NimGame {
    public static void main(String[] args) {
        //intiallize scanner
        Scanner scanner = new Scanner(System.in);
        //randomize pile size
        int pileSize = (int) (Math.random() * 30) + 21;
        //print welcome message
        System.out.println("Welcome to Nim!");
        //ask if player wants to go first
        System.out.println("Do you want to go first? (true  or false)");
        String strPlayerTurn = scanner.nextLine();
        boolean playerTurn;
        //check if input is valid
        while (!strPlayerTurn.equalsIgnoreCase( "true") && !strPlayerTurn.equalsIgnoreCase("false")) {
            System.out.println("Invalid input, please enter 'true' or 'false'.");
            strPlayerTurn = scanner.nextLine();
        }
        playerTurn = Boolean.parseBoolean(strPlayerTurn);
        //start game
        System.out.println("Starting pile size: " + pileSize);
        System.out.println(playerTurn ? "You go first!" : "Computer goes first!");
        System.out.println("Remember: The player who takes the last object loses!");
        //strat gameloop
        while (pileSize > 0) {
            System.out.println("Remaining objects: " + pileSize);
            if (playerTurn) {
                pileSize = playerMove(scanner, pileSize);
            } else {
                pileSize = computerMove(pileSize);
            }
            //determine winner
            playerTurn = !playerTurn;
        }
        //print winner
        System.out.println(playerTurn ? "You win!" : "Computer wins!");
        scanner.close();
    }
    //takes user input for how many objects to take
    public static int playerMove(Scanner scanner, int pileSize) {
        int take;
        //ask for user choice for how many objects to take
        System.out.print("How many objects do you want to take? (1-3): ");
        take = scanner.nextInt();
        //check if input is valid
        while (!(take >= 1 && take <= 3 && take <= pileSize)) {
            System.out.println("Invalid move. Please take 1, 2, or 3 objects.");
            System.out.print("How many objects do you want to take? (1-3): ");
            take = scanner.nextInt();
        }
        //print how many objects the player took
        System.out.println("You took " + take + " object(s).");
        return pileSize - take;
    }
    //determines how many objects the computer should take to maximize its chances of winning
    public static int computerMove(int pileSize) {
        int take;
        //determine how many objects the computer should take to maximize its chances of winning
        if (pileSize <= 3) {
            take = Math.max(pileSize - 1, 1);
        } else {
             int target = (pileSize - 1) % 4 == 0 ? nearestPowerOfTwoMinusOne(pileSize) : ((pileSize - 1) / 4) * 4 + 1;
            take = pileSize - target;
        if (take > 3 || take < 1) {
            take = 1;
        }
    }
    System.out.println("Computer took " + take + " object(s).");
    return pileSize - take;
    }
    public static int nearestPowerOfTwoMinusOne(int n) {
        // Use Integer.highestOneBit to find the largest power of two less than
        // or equal to n, then subtract one.
        int power = Integer.highestOneBit(n);
        return power - 1;
    }
}