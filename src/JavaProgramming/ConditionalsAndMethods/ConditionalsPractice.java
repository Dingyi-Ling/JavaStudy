package JavaProgramming.ConditionalsAndMethods;

import java.util.Scanner;
public class ConditionalsPractice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a program to run:");
        System.out.println("1. Combine Digits");
        System.out.println("2. String and Number Operation");
        System.out.println("3. Find Greatest Digit");
        System.out.println("4. Exit");
        System.out.print("Enter your choice (1-4): ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                CombineNumbers(scanner);
            case 2:
                StringAndNumbers(scanner);
            case 3:
                GreatestDigit(scanner);
            default:
                break;
        }
    }
    public static void CombineNumbers(Scanner keyboard) {
        System.out.println("Enter 4 digits (0-9)");
        int ones = keyboard.nextInt();
        int tens = keyboard.nextInt();
        int hundreds = keyboard.nextInt();
        int thousands = keyboard.nextInt();
        System.out.println(ones + tens * 10 + hundreds * 100 + thousands * 1000);
    }
    public static void StringAndNumbers(Scanner keyboard) {
        System.out.println("Enter a string and two numbers separated by spaces:");
        String operation = keyboard.next();
        int num1 = keyboard.nextInt();
        int num2 = keyboard.nextInt();
        if (operation.equals("%")) {
            System.out.println(num1 % num2);
        } else if (operation.equals("+")) {
            System.out.println(num1 + num2);
        } else if (operation.equals("-")) {
            System.out.println(num1 - num2);
        } else if (operation.equals("*")) {
            System.out.println(num1 * num2);
        } else if (operation.equals("/")) {
            System.out.println(num1 / num2);
        } else{
            System.out.println(operation + (char) num1 + (char) num2);
        }
    }
    public static void GreatestDigit(Scanner keyboard) {
        System.out.println("Enter a number (Between 0-999)");
        int number = keyboard.nextInt();
        if (number < 0 || number > 999) {
            System.out.println("Number out of range. Please enter a number between 0 and 999.");
            return;
        }
        int ones = number % 10;
        int tens = (number / 10) % 10;
        int hundreds = number / 100;
        int greatest;

        if (ones >= tens && ones >= hundreds) {
            greatest = ones;
        } else if (tens >= ones && tens >= hundreds) {
            greatest = tens;
        } else {
            greatest = hundreds;
        }

        System.out.println("The greatest digit is: " + greatest);
    }
}
