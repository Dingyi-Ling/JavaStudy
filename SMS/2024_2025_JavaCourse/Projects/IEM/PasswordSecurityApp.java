import java.util.Scanner;

public class PasswordSecurityApp {

    // Simple Cipher interface.
    interface Cipher {
        String encode(String text);
        String decode(String text);
    }

    // A simple Caesar cipher that shifts letters by a fixed amount.
    static class CaesarCipher implements Cipher {
        int shift;

        public CaesarCipher(int shift) {
            this.shift = shift;
        }

        @Override
        public String encode(String text) {
            StringBuilder result = new StringBuilder();
            for (char c : text.toCharArray()) {
                if (Character.isLetter(c)) {
                    char base = Character.isUpperCase(c) ? 'A' : 'a';
                    result.append((char) ((c - base + shift) % 26 + base));
                } else {
                    result.append(c);
                }
            }
            return result.toString();
        }

        @Override
        public String decode(String text) {
            StringBuilder result = new StringBuilder();
            for (char c : text.toCharArray()) {
                if (Character.isLetter(c)) {
                    char base = Character.isUpperCase(c) ? 'A' : 'a';
                    result.append((char) ((c - base - shift + 26) % 26 + base));
                } else {
                    result.append(c);
                }
            }
            return result.toString();
        }
    }

    // A simple columnar transposition cipher.
    // It writes the text into a grid with a fixed number of columns and reads column-by-column.
    static class TranspositionCipher implements Cipher {
        int columns;

        public TranspositionCipher(int columns) {
            this.columns = columns;
        }

        @Override
        public String encode(String text) {
            int len = text.length();
            int rows = (int) Math.ceil((double) len / columns);
            int paddedLength = rows * columns;
            // Pad with spaces if necessary.
            StringBuilder padded = new StringBuilder(text);
            while (padded.length() < paddedLength) {
                padded.append(" ");
            }
            StringBuilder cipherText = new StringBuilder();
            // Read the grid column by column.
            for (int col = 0; col < columns; col++) {
                for (int row = 0; row < rows; row++) {
                    int index = row * columns + col;
                    cipherText.append(padded.charAt(index));
                }
            }
            return cipherText.toString();
        }

        @Override
        public String decode(String text) {
            int rows = text.length() / columns;
            char[][] grid = new char[rows][columns];
            int index = 0;
            // Fill the grid column by column.
            for (int col = 0; col < columns; col++) {
                for (int row = 0; row < rows; row++) {
                    grid[row][col] = text.charAt(index++);
                }
            }
            // Read the grid row by row.
            StringBuilder plainText = new StringBuilder();
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < columns; col++) {
                    plainText.append(grid[row][col]);
                }
            }
            return plainText.toString().trim();
        }
    }

    // A simple crack time estimator.
    // It uses a basic estimation: (complexity factor)^(password length) / attempts per second.
    static class CrackEstimator {
        static long estimateCrackTime(String password, Cipher cipher) {
            int length = password.length();
            double complexityFactor = 10; // Default factor.
            if (cipher instanceof CaesarCipher) {
                complexityFactor = 26; // 26 possibilities per letter.
            } else if (cipher instanceof TranspositionCipher) {
                // For transposition, we use the number of columns as a rough factor.
                complexityFactor = ((TranspositionCipher) cipher).columns;
            }
            double attempts = Math.pow(complexityFactor, length);
            double attemptsPerSecond = 1000; // Simplified assumption.
            return (long) (attempts / attemptsPerSecond);
        }
    }

    // Main method: get user input, choose a cipher, encode the password, and estimate crack time.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        System.out.print("Enter cipher (caesar or transposition): ");
        String choice = scanner.nextLine();

        Cipher cipher;
        if (choice.equalsIgnoreCase("caesar")) {
            cipher = new CaesarCipher(3); // Fixed shift of 3.
        } else if (choice.equalsIgnoreCase("transposition")) {
            cipher = new TranspositionCipher(4); // Fixed 4 columns.
        } else {
            System.out.println("Invalid cipher choice.");
            scanner.close();
            return;
        }

        String encodedPassword = cipher.encode(password);
        System.out.println("Encoded Password: " + encodedPassword);

        long estimatedTime = CrackEstimator.estimateCrackTime(password, cipher);
        System.out.println("Estimated time to crack the password: " + estimatedTime + " seconds.");

        scanner.close();
    }
}
