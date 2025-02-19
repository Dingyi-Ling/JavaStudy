import java.util.Random;

public class PasswordSecurityApp {
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
    public static void main(String[] args) {
        //Test Cases
        AtbashCipher atbashCipher = new AtbashCipher();
        CaesarCipher caesarCipher = new CaesarCipher();
        String text = "Hello World";
        String caeserEncryptedText = atbashCipher.encrypt(text);
        String caeserDecryptedText = atbashCipher.decrypt(caeserEncryptedText);
        String atbashEncryptedText = caesarCipher.encrypt(text);
        String atbashDecryptedText = caesarCipher.decrypt(atbashEncryptedText);
        System.out.println("Original Text: " + text);
        System.out.println("Caeser Encrypted Text: " + caeserEncryptedText);
        System.out.println("Caeser Decrypted Text: " + caeserDecryptedText);
        System.out.println("Atbash Encrypted Text: " + atbashEncryptedText);
        System.out.println("Atbash Decrypted Text: " + atbashDecryptedText);
    }
}
