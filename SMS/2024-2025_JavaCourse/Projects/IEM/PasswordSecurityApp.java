import java.util.Random;
import java.util.Scanner;
public class PasswordSecurityApp {
    //Caesar Cipher is a substitution cipher where each letter in the plaintext is replaced by a letter some fixed number of positions down the alphabet.
    static class CaesarCipher{
        private static Random random = new Random();
        //creates a random shift value from 1 to 5
        private static int shift = random.nextInt(5) + 1;
        public CaesarCipher() {}
        //encrypts the text by shifting the characters by the shift value
        public String encrypt(String text){
            String encryptedText = "";
            for (int i = 0; i < text.length(); i++) {
                char ch = text.charAt(i);
                ch = (char) (ch + shift);
                encryptedText += ch;
            }
            return encryptedText;
        }
        //decrypts the text by shifting the characters back by the shift value
        public String decrypt(String text){
            String decryptedText = "";
            for (int i = 0; i < text.length(); i++) {
                char ch = text.charAt(i);
                ch = (char) (ch - shift);
                decryptedText += ch;
            }
            return decryptedText;
        }
    }
    //Atbash Cipher is a substitution cipher where each letter in the plaintext is replaced with the letter at the same position in the alphabet but in reverse.
    static class AtbashCipher{
        public AtbashCipher() {}
        // Since Atbash is symmetric, encryption and decryption are identical.
        public String encrypt(String text) {
            String encryptedText = "";
            for (char ch : text.toCharArray()) {
                if (ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z') {
                    if (ch >= 'A' && ch <= 'Z') {
                        ch = (char) ('Z' - (ch - 'A'));
                    } else {
                        ch = (char) ('z' - (ch - 'a'));
                    }
                }
                encryptedText += ch;
            }
            return encryptedText;
        }
        public String decrypt(String text) {
            return encrypt(text);
        }
    }
    class PasswordDecryptor{
        //The estimated time to crack a password is based on the number of possible combinations of characters in the password.
        private String encrypted;
        public PasswordDecryptor(){}
        public void setEncrypted(String encrypted){
            this.encrypted = encrypted;
        }
        public double estimateCrackTime(){
            //Assuming that a computer can check 1 billion combinations per second.
            double combinations = 0;
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
    public static void PasswordSecurityExplorer(){
        //The PasswordSecurityExplorer method is used to explore the security of a password by encrypting and decrypting it using different methods.
        AtbashCipher atbashCipher = new AtbashCipher();
        CaesarCipher caesarCipher = new CaesarCipher();
        Scanner scanner = new Scanner(System.in);
        String introduction = "";
        System.out.println(introduction);
        System.out.println("\n==== Interactive Password Security Explorer ====");
        String password = "";
        //Used do while loop, learnt from Stack Overflow https://stackoverflow.com/questions/20472169/when-would-a-do-while-loop-be-the-better-than-a-while-loop
        System.out.print("\nEnter a password: ");
        password = scanner.nextLine();
        while (password.indexOf(" ") != -1) {
            System.out.println("Password cannot contain spaces. Please re-enter.");
            System.out.print("\nEnter a password: ");
            password = scanner.nextLine();
        }
        System.out.println("\nSelect encryption method:\n1. Caesar Cipher\n2. Atbash Cipher\n3.No Encryption");
        int choice = scanner.nextInt(); 
        String encrypted = "";
        System.out.print("Enter your choice (1, 2 or 3): ");
        choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left by nextInt()
        while (choice != 1 && choice != 2) {
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
        PasswordDecryptor passwordDecryptor = new PasswordSecurityApp().new PasswordDecryptor();
        passwordDecryptor.setEncrypted(encrypted);
        double combinations = passwordDecryptor.estimateCrackTime();
        System.out.println("Estimated time to crack: " + combinations + " seconds");
        scanner.close();
    }
    public static void simplePasswordCrackTimeEstimate(String password){
        PasswordDecryptor passwordDecryptor = new PasswordSecurityApp().new PasswordDecryptor();
        passwordDecryptor.setEncrypted(password);
        double time = passwordDecryptor.estimateCrackTime();
        System.out.println("Estimated time to crack: " + time + " seconds");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("======Introduction to Ciphers and Connection to Data Security======");
        System.out.println("Our program addresses the important issue of password security by showing how encryption affects the strength of passwords. \nThe main problem is that weak passwords are vulnerable to cyberattacks, especially brute force attacks, where hackers try different combinations until they find the correct one. \nMany people don't realize the importance of having strong passwords, which makes them easy targets for cybercriminals.");
        System.out.println("For example try entering '123456' as your password.");
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        simplePasswordCrackTimeEstimate(password);
        System.out.println("As seen the time taken to crack the password is almost instant.");
        System.out.println("Now try entering 'password' as your password.");
        password = scanner.nextLine();
        simplePasswordCrackTimeEstimate(password);
        System.out.println("As seen the time taken to crack the password is much slower.");
        System.out.println("The process above should show you  risks of weak passwords, and shows real-world data breaches. \nAt this point you might see how important password security is today by knowing the real case of data breaches.");
        System.out.println("Now consider a new component encryption methods, the choice of encryption method could also make a huge differnce in the security of your code");
        System.out.println("Do you want a short introduction to ciphers? (y or n)");
        if(scanner.nextLine() == "y") {
            System.out.println("In this program we mainly focused on two simple ciphers to demonstrate the importance of it: \n Caeser Cipher and Atbash Cipher.");
            System.out.println("Caesar Cipher is a substitution cipher where each letter in the plaintext is replaced by a letter some fixed number of positions down the alphabet.");
            System.out.println("Atbash Cipher is a substitution cipher where each letter in the plaintext is replaced with the letter at the same position in the alphabet but in reverse.");
            
        }


    }
    //Aiden Part Ends
}
