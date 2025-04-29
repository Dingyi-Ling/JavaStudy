package JavaProgramming.ConditionalsAndMethods;

import java.util.Scanner;

public class FunWithIntegers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 1 for Cash Register");
        System.out.println("Enter 2 for Multiplication Quiz");
        System.out.println("Enter 3 for Hailstone Sequence");
        System.out.println("Enter any other number to exit");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                CashRegister(scanner);
                break;
            case 2:
                MultiplicationQuiz(scanner);
                break;
            case 3:
                HailstoneSequence(scanner);
                break;
            default:
                break;
        }

    }

    public static void CashRegister(Scanner keyboard) {
        int total = 0;
        int input;
        System.out.println("Enter a number (0 to quit): ");
        input = keyboard.nextInt();

        while (input != 0) {
            total += input;
            System.out.println("Subtotal: " + total);
            System.out.println("Enter a number (0 to quit): ");
            input = keyboard.nextInt();
        }

        System.out.println("Total: " + total);


    }

    public static void MultiplicationQuiz(Scanner keyboard) {
        int correctQuestions = 0;
        int wrongQuestions = 0;
        int num1;
        int num2;
        while (correctQuestions != 10) {
            num1 = (int) (Math.random() * 10) + 2;
            num2 = (int) (Math.random() * 10) + 2;
            System.out.print(num1 + " x " + num2 + " = ");
            if (keyboard.nextInt() == num1 * num2) {
                System.out.println("Correct");
                correctQuestions++;
                System.out.println("Correct Questions: " + correctQuestions);
                System.out.println("Wrong Questions: " + wrongQuestions);
            } else {
                System.out.println("Wrong");
                wrongQuestions++;
                System.out.println("Correct Questions: " + correctQuestions);
                System.out.println("Wrong Questions: " + wrongQuestions);
            }
        }
        float CorrectPercentage = ((float) correctQuestions / (correctQuestions + wrongQuestions)) * 100;
        System.out.println("Correct Percentage: " + (int) (CorrectPercentage) + "%");
    }

    public static void HailstoneSequence(Scanner keyboard) {
        System.out.println("Enter a positive integer: ");
        int num1 = keyboard.nextInt();
        while (num1 != 1) {
            if (num1 % 2 == 0) {
                num1 = num1 / 2;
            } else if (num1 % 2 == 1) {
                num1 = num1 * 3;
                num1++;
            }
            System.out.print(num1 + " ");
        }

    }
}


