package SpellingBee;
import java.util.*;

class SpellingBeeGame {
    private List<Character> letters;
    private char centerLetter;
    private List<String> dictionary;
    private List<String> foundWords;
    private int totalScore;

    public SpellingBeeGame() {
        dictionary = loadDictionary("dictionary.txt"); // Load words from file
        letters = generateLettersFromDictionary();
        centerLetter = letters.get(0);
        foundWords = new ArrayList<>();
        totalScore = 0;
    }

    // Reads words from dictionary file and stores them in an ArrayList
    private ArrayList<String> loadDictionary(String filename) {
        ArrayList<String> wordList = new ArrayList<>();
        Scanner scanner = new Scanner(filename);
        while (scanner.hasNextLine()) {
            String word = scanner.nextLine();
            if (word.length() >= 4) { // Store only words of at least 4 letters
                wordList.add(word);
            }
        }
        scanner.close();
        return wordList;
    }

    // Picks a random 7-letter word from the dictionary and shuffles its letters
    private List<Character> generateLettersFromDictionary() {
        List<String> sevenLetterWords = new ArrayList<>();
        for (String word : dictionary) {
            if (word.length() == 7) {
                sevenLetterWords.add(word);
            }
        }

        if (sevenLetterWords.isEmpty()) {
            System.out.println("Error: No 7-letter words in dictionary.");
            System.exit(1);
        }

        Collections.shuffle(sevenLetterWords); // Pick a random word
        String chosenWord = sevenLetterWords.get(0);

        // Convert word to a list of characters and shuffle
        List<Character> letterList = new ArrayList<>();
        for (char ch : chosenWord.toCharArray()) {
            letterList.add(ch);
        }
        Collections.shuffle(letterList);
        return letterList;
    }

    private boolean isValidWord(String word) {
        if (word.length() < 4) {
            System.out.println("❌ The word must be at least 4 letters long.");
            return false;
        }
        if (!word.contains(String.valueOf(centerLetter))) {
            System.out.println("❌ The word must include the center letter '" + centerLetter + "'.");
            return false;
        }
        for (char ch : word.toCharArray()) {
            if (!letters.contains(ch)) {
                System.out.println("❌ The word contains invalid letters.");
                return false;
            }
        }
        if (!dictionary.contains(word)) {
            System.out.println("❌ \"" + word + "\" is not a valid word.");
            return false;
        }
        if (foundWords.contains(word)) {
            System.out.println("❌ You already entered this word.");
            return false;
        }
        return true;
    }

    private int calculateScore(String word) {
        int points = (word.length() == 4) ? 1 : word.length();
        boolean isPangram = true;
        for (char ch : letters) {
            if (!word.contains(String.valueOf(ch))) {
                isPangram = false;
                break;
            }
        }
        if (isPangram) {
            points += 7;
            System.out.println("✅ Pangram! +7 bonus!");
        } else {
            System.out.println("✅ Good word!");
        }
        return points;
    }

    public void startGame() {
        System.out.println("Your letters: " + letters);
        System.out.println("Center letter: " + centerLetter);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\nEnter a word (or 'quit' to stop): ");
            String word = scanner.nextLine().trim().toUpperCase();

            if (word.equals("QUIT")) {
                break;
            }

            if (isValidWord(word)) {
                foundWords.add(word);
                int points = calculateScore(word);
                totalScore += points;
                System.out.println("Added \"" + word + "\" for " + points + " points. Total: " + totalScore);
            }
        }
        scanner.close();
        endGame();
    }

    private void endGame() {
        System.out.println("\n=== Game Over ===");
        System.out.println("Words found: " + foundWords);
        System.out.println("Final score: " + totalScore);
    }
}

// Main class
public class SpellingBeeMain {
    public static void main(String[] args) {
        SpellingBeeGame game = new SpellingBeeGame();
        game.startGame();
    }
}
