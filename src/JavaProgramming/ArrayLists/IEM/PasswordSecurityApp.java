package JavaProgramming.ArrayLists.IEM;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class PasswordSecurityApp {
    //Dingyi's Part Starts
    //Caesar Cipher is a substitution cipher where each letter in the plaintext is replaced by a letter some fixed number of positions down the alphabet.
    static class CaesarCipher{
        private static final Random random = new Random();
        //creates a random shift value from 1 to 5
        private static final int shift = random.nextInt(5) + 1;
        public CaesarCipher() {}
        //encrypts the text by shifting the characters by the shift value
        public String encrypt(String text){
            StringBuilder encryptedText = new StringBuilder();
            for (int i = 0; i < text.length(); i++) {
                char ch = text.charAt(i);
                ch = (char) (ch + shift);
                encryptedText.append(ch);
            }
            return encryptedText.toString();
        }
    }
    //Atbash Cipher is a substitution cipher where each letter in the plaintext is replaced with the letter at the same position in the alphabet but in reverse.
    static class AtbashCipher{
        public AtbashCipher() {}
        // Since Atbash is symmetric, encryption and decryption are identical.
        public String encrypt(String text) {
            StringBuilder encryptedText = new StringBuilder();
            for (char ch : text.toCharArray()) {
                if (ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z') {
                    if (ch <= 'Z') {
                        ch = (char) ('Z' - (ch - 'A'));
                    } else {
                        ch = (char) ('z' - (ch - 'a'));
                    }
                }
                encryptedText.append(ch);
            }
            return encryptedText.toString();
        }
    }
    static class PasswordDecryptor{
        //The estimated time to crack a password is based on the number of possible combinations of characters in the password.
        private String encrypted;
        public PasswordDecryptor(){}
        public void setEncrypted(String encrypted){
            this.encrypted = encrypted;
        }
        public double estimateCrackTime(){
            //Assuming that a computer can check 1 billion combinations per second.
            double combinations;
            combinations = Math.pow(encrypted.length(), calculateSize());
            combinations = combinations / 1000000000;
            return combinations;
        }
        private int calculateSize(){
            //Complexity is based on the number of possible combinations of characters in the password.
            int complexity = 0;
            boolean hasLowerCase = false;
            boolean hasUpperCase = false;
            boolean hasNumber = false;
            boolean hasSpecialChar = false;
            for(char ch: encrypted.toCharArray()) {
                if(ch >= 'a' && ch <= 'z') {
                    hasLowerCase = true;
                } else if(ch >= 'A' && ch <= 'Z') {
                    hasUpperCase = true;
                } else if(ch >= '0' && ch <= '9') {
                    hasNumber = true;
                } else {
                    hasSpecialChar = true;
                }
            }
            if (hasLowerCase) {complexity += 26;}
            if (hasUpperCase) {complexity += 26;}
            if (hasNumber) {complexity += 10;}
            if (hasSpecialChar) {complexity += 33;}
            if(complexity > 0){
                return complexity;
            } else{
                return 1;
            }
        }
    }
    //Dingyi's Part Ends
    //Adien's Part Starts
    public static void comparisonBetweenCiphers(Scanner scanner){
        //The comparisonBetweenCiphers method is used to compare the security of two passwords encrypted by two different methods.
        AtbashCipher atbashCipher = new AtbashCipher();
        CaesarCipher caesarCipher = new CaesarCipher();
        System.out.print("\n Enter a password: ");
        String password = scanner.nextLine();
        String encryptedByAtbash = atbashCipher.encrypt(password);
        String encryptedByCaesar = caesarCipher.encrypt(password);
        System.out.println("Encrypted by Atbash: " + encryptedByAtbash);
        System.out.println("Encrypted by Caesar: " + encryptedByCaesar);
        PasswordDecryptor passwordDecryptor = new PasswordDecryptor();
        passwordDecryptor.setEncrypted(encryptedByAtbash);
        double timeForAtbash = passwordDecryptor.estimateCrackTime();
        passwordDecryptor.setEncrypted(encryptedByCaesar);
        double timeForCaesar = passwordDecryptor.estimateCrackTime();
        System.out.println("Estimated time to crack by Atbash: " + timeForAtbash + " seconds");
        System.out.println("Estimated time to crack by Caesar: " + timeForCaesar + " seconds");
    }
    public static void PasswordSecurityExplorer(Scanner scanner){
        //The PasswordSecurityExplorer method is used to explore the security of a password by encrypting and decrypting it using different methods.
        AtbashCipher atbashCipher = new AtbashCipher();
        CaesarCipher caesarCipher = new CaesarCipher();
        System.out.println("\n==== Cipher Password Security Explorer ====");
        String password = "initial";
        String encrypted = "";
        while (!password.isEmpty()) {
            System.out.print("\nEnter a password (or press Enter to exit): ");
            password = scanner.nextLine();
            
            if (!password.isEmpty()) {
                // Check for spaces in password
                while (password.contains(" ")) {
                    System.out.println("Password cannot contain spaces. Please re-enter.");
                    System.out.print("\nEnter a password: ");
                    password = scanner.nextLine();
                }
                
                System.out.println("\nSelect encryption method:\n1. Caesar Cipher\n2. Atbash Cipher\n3.No Encryption");
                int choice;
                System.out.print("Enter your choice (1, 2 or 3): ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left by nextInt()
                
                while (choice != 1 && choice != 2 && choice != 3) {
                    System.out.println("Invalid choice. Please enter 1, 2 or 3.");
                    System.out.print("Enter your choice (1, 2 or 3): ");
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left by nextInt()
                }
                
                switch(choice){
                    case 1:
                        encrypted = caesarCipher.encrypt(password);
                        System.out.println("Encrypted password: " + encrypted);
                        break;
                    case 2:
                        encrypted = atbashCipher.encrypt(password);
                        System.out.println("Encrypted password: " + encrypted);
                        break;
                    case 3:
                        encrypted = password;
                        System.out.println("No encryption selected.");
                        break;
                }
                
                PasswordDecryptor passwordDecryptor = new PasswordDecryptor();
                passwordDecryptor.setEncrypted(encrypted);
                double combinations = passwordDecryptor.estimateCrackTime();
                System.out.println("Estimated time to crack: " + combinations + " seconds\n");
            }
        }
    }
    public static void simplePasswordCrackTimeEstimate(String password){
        //The simplePasswordCrackTimeEstimate method is used to estimate the time it would take to crack a password.
        System.out.println("\n==== Simple Password Crack Time Estimate ====");
        PasswordDecryptor passwordDecryptor = new PasswordDecryptor();
        passwordDecryptor.setEncrypted(password);
        double time = passwordDecryptor.estimateCrackTime();
        System.out.println("Estimated time to crack: " + time + " seconds");
    }
    //Adien's Part Ends
    //Dingyi's Part Starts
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("======Introduction to Ciphers and Connection to Data Security======");
        System.out.println("Our program addresses the important issue of password security by showing how encryption affects the strength of passwords. \nThe main problem is that weak passwords are vulnerable to cyberattacks, especially brute force attacks, where hackers try different combinations until they find the correct one. \nMany people don't realize the importance of having strong passwords, which makes them easy targets for cybercriminals.");
        System.out.println();
        System.out.println("For example try entering '123456' as your password.");
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        simplePasswordCrackTimeEstimate(password);
        System.out.println("As seen the time taken to crack the password is almost instant.");
        System.out.println("Now try entering 'password' as your password.");
        System.out.print("Enter your password: ");
        password = scanner.nextLine();
        simplePasswordCrackTimeEstimate(password);
        System.out.println("As seen the time taken to crack the password is much slower.");
        System.out.println("The process above should shows you risks of weak passwords, and shows real-world data breaches. \nAt this point you might see how important password security is today by knowing the real case of data breaches.");
        System.out.println();
        System.out.println("Now consider a new component encryption methods, the choice of encryption method could also make a huge differnce in the security of your code");
        System.out.println("Do you want a short introduction to ciphers? (1 for yes, 2 for no)");
        int response = scanner.nextInt();
        if (response != 1) {
            System.out.println("No way you're escaping this session, its so important to know!!!!");
            System.out.println("You have no choice (just checking if your following)");
            System.out.println("Lesson Starts!");
        }
        System.out.println("In this program we mainly focused on two simple ciphers to demonstrate the importance of it: \n Caeser Cipher and Atbash Cipher.");
        System.out.println("Caesar Cipher is a substitution cipher where each letter in the plaintext is replaced by a letter some fixed number of positions down the alphabet.");
        System.out.println("Atbash Cipher is a substitution cipher where each letter in the plaintext is replaced with the letter at the same position in the alphabet but in reverse.");
        System.out.println("Bellow is a short game you can play to see the difference between the two.");
        comparisonBetweenCiphers(scanner);
        System.out.println();
        System.out.println("Password Strength");
        System.out.println("From the example above you should gain some insights into the importance of password security.");
        System.out.println("Now focus on the influence of capital letters, and special characters in passwords. Use the game bellow to help you explore its impacts to its strngth.");
        System.out.println("You can play the game however mant time you want to see the difference.");
        PasswordSecurityExplorer(scanner);
        System.out.println("Now as you played the games multiple times what did you realize? Type your insights down in the text box bellow. \n(if you need to play the game again leave the text box empty and press enter))");
        System.out.print("\nInsights:");
        String insights = scanner.nextLine();
        if (Objects.equals(insights, "")){ //if the user wants to play the game again
            PasswordSecurityExplorer(scanner);
        }
        if(insights.length() > 50){
            System.out.println("Thank you for your insights. Looks like you have learned alot!!!");
        } else {
            System.out.println("Thank you for your insights. Looks like you have learned something!!!");
        }
        System.out.println("Bellow we provided some insights you should have gained from the game.");
        System.out.println("Encrypted the password→the program prints out an estimated time how long it would take a hacker to crack it (brute force attack). \nThis is important because it directly shows how the length and complexity of a password affect security.");
        System.out.println("Short password → cracked instantly.");
        System.out.println("A slightly longer one → takes a few seconds.");
        System.out.println("A complex password → could take years to crack.");
        System.out.println("Password Length > Complexity - a 12 character password with only one lower case letter could be more secure than an 8 character password with symbols, numbers, abd uppercase letters.");
        System.out.println("This challenges the common belief that adding symbols always makes a password stronger. Length is important because longer passwords exponentially increase the number of possible combinations.");
        System.out.println("As always feel free to add anything else to our program your response will be stored and demonstrated to the next participant"); //Function does not work as we don't understand how to do this, could you teach us Mr.L?
        System.out.print("\nAddition:");
        String addition = scanner.nextLine();
        ArrayList <String> responses = new ArrayList<>();
        if(!Objects.equals(addition, "")) {
            responses.add(addition);
            System.out.println("Thank you for your response.");
        }
        System.out.println("Do you want to learn about how our program estimates the time needed to crack a password? (1 for yes, 2 for no)");
        response = scanner.nextInt();
        if(response == 1) { 
            System.out.println("The program uses the simplest brutal attack method to estimate the time needed to crack a password.");
            System.out.println("It find the possible combonations of the password by determing if there is a lower case letter, upper case letter, number, ot special character.");
            System.out.println("Then it calculates the length of the password to the power of combinations.");
            System.out.println("The program then divides the number of combinations by the number of passwords it can check in a second.");
            System.out.println("The result is the estimated time needed to crack the password.");
            System.out.println("This is a very simple method and is not very accurate. It is only used to give a rough estimate of the time needed to crack a password.");
            System.out.println("The actual time needed to crack a password depends on the method used and the computer used to crack it.");
        } 
        System.out.println("Thank you for participating in our program, we hope you learned something new.");
        System.out.println("Do you want to send your insights to your email adress? (1 for yes, 2 for no)");
        response = scanner.nextInt();
        if (response == 1) {
            System.out.println("What is your name?");
            String name = scanner.nextLine();
            System.out.println("What is your email address?");
            String email = scanner.nextLine();
            System.out.println("Thank you! Your insights will be sent to " + email + " shortly.");
            System.out.println("Email Sent: ");
            System.out.println("Dear " + name + ",");
            System.out.println("Thank you for participating in our program, we hope you learned something new.");
            System.out.println("Your insights:");
            System.out.println(insights);
            System.out.println("Your addition:");
            System.out.println(addition);
            System.out.println("We hope to see you again soon!");
            System.out.println("Best regards,");
            System.out.println("Aiden & Dingyi");
        } else {
            System.out.println("Thank you! See you next time!");
        }
        scanner.close();
    }
    //Dingyi's Part Ends
}