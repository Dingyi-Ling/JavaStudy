package JavaProgramming.ArrayLists.SpellingBee;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SpellingBeeGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Character> letters = new ArrayList<>();
        ArrayList<String> words = new ArrayList<>();
        Random rand = new Random();
        int score = 0;
        while (letters.size() < 7) {
            char letter = (char) ('a' + rand.nextInt(26));
            if (!letters.contains(letter)) {
                letters.add(letter);
            }
        } 
        char centerLetter = letters.get(rand.nextInt(7));
        System.out.println("Welcome to Spelling Bee!");
        System.out.println("Allowed letters: " + letters);
        System.out.println("Center letter: " + centerLetter);
        System.out.println("Enter words (or press Enter with no input to end the game):");

        String input;
        while (!(input = scanner.nextLine()).isEmpty()) {
            if (words.contains(input)) {
                System.out.println("You have already entered this word.");
                continue;
            }
            
            if (input.length() < 4) {
                System.out.println("Word must be at least 4 letters long.");
                continue;
            }
            
            if (input.indexOf(centerLetter) == -1) {
                System.out.println("Word must contain the center letter.");
                continue;
            }
            
            boolean validWord = true;
            for (char letter : input.toCharArray()) {
                if (!letters.contains(letter)) {
                    System.out.println("Word must contain only the allowed letters.");
                    validWord = false;
                    break;
                }
            }
            
            if (validWord) {
                words.add(input);
                System.out.println("Word accepted! You have found " + words.size() + " words.");
            }
        }
        System.out.println("Game Over!");
        System.out.println("You found " + words.size() + " words:");
        System.out.println(words);
        for (String word : words) {
            if(word.length()==4){
                score += 1;
            } else {
                score += word.length();
            }
            boolean isPangram = true;
            for (char letter : letters) {
                if (word.indexOf(letter) == -1) {
                    isPangram = false;
                    break;
                }
            }
            if (isPangram) {
                score += 7;
                System.out.println(word + " is a pangram! (uses all letters)");
            }
        }
        System.out.println("Your score is " + score);
        scanner.close();
    }
}