package JavaProgramming.LoopsAndLogic;

import java.util.Scanner;

public class DoubleTrouble {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        // Display menu options to the user
        System.out.println("Enter 1 for CashRegister");
        System.out.println("Enter 2 for GregorySeries");
        System.out.println("Enter 3 to exit");

        // Read user's choice
        int choice = new Scanner(System.in).nextInt();

        // Execute the corresponding method based on user's choice
        switch (choice) {
            case 1:
                CashRegister();
                break;
            case 2:
                GregorySeries();
                break;
            default:
                break;
        }
    }
    public static void CashRegister(){
        // Create a Scanner object to read user input
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        double price = 1.00; 
        double subtotal = 0;
        double totalTax = 0;

        // Display the store name and instructions
        System.out.println("MA Clothing Store Register");
        System.out.println("Enter prices (0 to finish)");

        // Loop to read prices until the user enters 0
        while(price != 0){
            System.out.print("Enter price: $ ");
            price = scanner.nextDouble();

            // Check for negative prices
            if (price < 0) {
            System.out.println("Price cannot be negative");
            continue;
            }

            // Calculate tax for prices above $175
            double tax = 0;
            if (price > 175) {
            tax = (price - 175) * 0.0625;
            }

            // Update subtotal and total tax
            subtotal += price;
            totalTax += tax;
        }

        // Convert subtotal and total tax to cents to avoid floating-point precision issues
        int subtotalCents = (int) Math.round(subtotal * 100);
        int totalTaxCents = (int) Math.round(totalTax * 100);
        int totalCents = subtotalCents + totalTaxCents;

        // Format the cents to ensure two decimal places
        String subtotalFormatted = (subtotalCents / 100) + "." + (subtotalCents % 100 < 10 ? "0" + (subtotalCents % 100) : subtotalCents % 100);
        String totalTaxFormatted = (totalTaxCents / 100) + "." + (totalTaxCents % 100 < 10 ? "0" + (totalTaxCents % 100) : totalTaxCents % 100);
        String totalFormatted = (totalCents / 100) + "." + (totalCents % 100 < 10 ? "0" + (totalCents % 100) : totalCents % 100);

        // Display the receipt
        System.out.println("=== RECEIPT ===");
        System.out.println("Subtotal: $" + subtotalFormatted);
        System.out.println("Tax: $" + totalTaxFormatted);
        System.out.println("Total: $" + totalFormatted);

    }
    public static void GregorySeries(){
        try (Scanner scanner = new Scanner(System.in)) {
            // Prompt the user to enter the number of terms
            System.out.println("Enter the number of terms to calculate in the Gregory series: ");
            int n = scanner.nextInt();

            // Check if the input is a positive integer
            if (n <= 0) {
            System.out.println("Please enter a positive integer.");
            return;
            }

            double sum = 0;

            // Calculate the sum of the Gregory series up to n terms
            for (int i = 1; i <= n; i++) {
            if (i % 2 == 1) {
                sum += 4.0 / (2 * i - 1);
            } else {
                sum -= 4.0 / (2 * i - 1);
            }
            }

            // Display the results
            System.out.println("Sum of the first " + n + " terms of the Gregory series: " + sum);
            System.out.println("For comparison, pi is: " + Math.PI);
            System.out.println("Difference from pi: " + Math.abs(sum - Math.PI));

        } catch (Exception e) {
            // Handle invalid input
            System.out.println("Invalid input. Please enter a valid integer.");
        }
    }
}