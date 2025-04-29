import java.util.Scanner;
public class MathMethods {
    public static void test_positive(int number){
        if (number > 0){
            System.out.println("Positive");
        } else if (number < 0) {
            System.out.println("Negative");

        } else{
            System.out.println("Zero");
        }
    }
    public static void test_even(int number){
        if (number % 2 == 0){
            System.out.println("Even");
        } else{
            System.out.println("Odd");
        }
    }
    public static void test_upper(int letter){
        if (letter >= 65 && letter <= 90){
            System.out.println("Upper");
        }
        else if(letter >= 97 && letter <= 122){
            System.out.println("Lower");
        }
        else{
            System.out.println("Neither");
        }
    }
    public static void test_multiple_ten(int number){
        if (number % 10 == 0){
            System.out.println("Multiple of ten");
        }
        else{
            System.out.println("Not a multiple of ten");
        }
    }
    public static void determine_biggest(double num1, double num2, double num3){
        if (num1 > num2){
            if (num1 > num3){
                System.out.println("Num 1 is the biggest");
            }
            else {
                System.out.println("Num 3 is the biggest");
            }
        }
        else{
            if (num2 > num3){
                System.out.println("Num 2 is the biggest");
            }
            else {
                System.out.println("Num 3 is the biggest");
            }
        }
    }
    public static void main(String[] args) {
        while (1>0){
            @SuppressWarnings("resource")
            Scanner keyboard = new Scanner(System.in);
            System.out.println("If you want to determine if a integer is positive press 1");
            System.out.println("If you want to determine if a integer is even press 2");
            System.out.println("If you want to determine if a character is uppercase, lowercase, or neither press 3");
            System.out.println("If you want to determine if a character is a multiple of ten press 4");
            System.out.println("If you want to determine the biggest double in three numbers press 5");
            int user_choice = keyboard.nextInt();
            if (user_choice == 1){
                System.out.println("Enter an integer");
                int user_input = keyboard.nextInt();
                test_positive(user_input);
            } else if(user_choice == 2){
                System.out.println("Enter an integer");
                int user_input = keyboard.nextInt();
                test_even(user_input);
            } else if(user_choice == 3){
                System.out.println("Enter an character");
                char user_input = keyboard.next().charAt(0);
                test_upper(user_input);
            } else if(user_choice == 4){
                System.out.println("Enter an integer");
                int user_input = keyboard.nextInt();
                test_multiple_ten(user_input);
            } else if(user_choice == 5){
                System.out.println("Enter the first double");
                double user_input = keyboard.nextDouble();
                System.out.println("Enter the second double");
                double user_input2 = keyboard.nextDouble();
                System.out.println("Enter the third double");
                double user_input3 = keyboard.nextDouble();
                determine_biggest(user_input,user_input2,user_input3);
        }

        }
    }
}
