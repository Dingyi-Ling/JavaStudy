package JavaProgramming.LoopsAndLogic;

import java.util.Scanner;
public class TextArt {
    public static void printDescendingStars(int n) {
        for (int i = n; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public static void printTreePattern(int height) {
        height +=1;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height - i - 1; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print("&");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 1 for printDescendingStars");
        System.out.println("Enter 2 for printTreePattern");
        System.out.println("Enter 3 to exit");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Enter a number: ");
                int n = scanner.nextInt();
                printDescendingStars(n);
                break;
            case 2:
                System.out.print("Enter a number: ");
                int height = scanner.nextInt();
                printTreePattern(height);
                break;
            default:
                break;
        }
    }
}






















