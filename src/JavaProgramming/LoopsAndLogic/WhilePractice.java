package JavaProgramming.LoopsAndLogic;

import java.util.Scanner;

public class WhilePractice {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        System.out.println("Welcome to While Practice!");
        System.out.println("Enter 1 for printNumbers");
        System.out.println("Enter 2 for findSqrt");
        System.out.println("Enter 3 for findLargest");
        System.out.println("Enter 4 for exponentialSequence");
        System.out.println("Enter 5 to exit");
        switch (new Scanner(System.in).nextInt()){
            case 1:
                printNumbers();
                break;
            case 2:
                findSqrt();
                break;
            case 3:
                findLargest();
                break;
            case 4:
                exponentialSequence();
                break;
            case 5:
                System.out.println("Goodbye!");
                break;
            default:
                break;
        }
    }
    public static void printNumbers(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter two numbers use a space to separate them: ");
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
        int n = Math.min(num1, num2);
        while(n <= Math.max(num1, num2)){
            System.out.print(n + " ");
            n++;
        }
        scanner.close();
    }
    public static void findSqrt(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a number: ");
        int num = scanner.nextInt();
        int n = 1;
        while(n*n != num){
            if (n*n > num){
                System.out.println("Does not have a perfect square root.");
                scanner.close();
                return;
            }
            n++;
        }
        System.out.println("The square root of " + num + " is " + n);
        scanner.close();
    }
    public static void findLargest(){
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        int largest = 0;
        System.out.println("How many integers would you like to enter?");
        int n = scanner.nextInt();
        while (n > 0) {
            System.out.print("Please enter an integer: ");
            int num = scanner.nextInt();
            if (num >= largest) {
                largest = num;
            }
            n--;
        }
        System.out.println("The largest number is " + largest);
    }
    public static void exponentialSequence(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the base (b): ");
        double b = scanner.nextDouble();
        System.out.print("Enter the coefficient (a): ");
        double a = scanner.nextDouble();
        int x = 0;
        double term = a;
        while (x < 6) {
            System.out.print(Math.round(term) + " ");
            term *= b;
            x++;
        }
        scanner.close();
    }
}
