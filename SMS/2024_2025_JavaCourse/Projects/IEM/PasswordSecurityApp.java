import java.util.Scanner;

public class PasswordSecurityApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 1. Get the user's password.
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        
        // 2. Ask for the cipher choice.
        System.out.print("Enter the cipher you want to use (e.g., caesar): ");
        String cipherChoice = scanner.nextLine();
        
        // 3. Instantiate the cipher using the factory.
        Cipher cipher;
        try {
            cipher = CipherFactory.getCipher(cipherChoice);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            scanner.close();
            return;
        }
        
        // 4. Encode the password.
        String encodedPassword = cipher.encode(password);
        System.out.println("Encoded Password: " + encodedPassword);
        
        // 5. Estimate crack time.
        long estimatedTimeSeconds = CrackEstimator.estimateCrackTime(password, cipher);
        System.out.println("Estimated time to crack the password: " + estimatedTimeSeconds + " seconds.");
        
        scanner.close();
    }
}
