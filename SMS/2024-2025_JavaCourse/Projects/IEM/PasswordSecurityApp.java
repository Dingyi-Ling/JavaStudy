import java.util.Random;
import java.util.Scanner;
// Dingyi Part Starts
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
        private String encrypted;
        public PasswordDecryptor(){}
        public void setEncrypted(String encrypted){
            this.encrypted = encrypted;
        }
        public double estimateCrackTime(){
            double combinations = 0;
            combinations = Math.pow(encrypted.length(), calculateSize());
            combinations = combinations / 1000000000;
            return combinations;
        }
        private int calculateSize(){
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
    //Dingyi Part Ends
    //Aiden Part Starts
    public static void main(String[] args) {
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
    //Aiden Part Ends
}
