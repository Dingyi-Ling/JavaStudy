package JavaProgramming.ArraysAndObjects;

public class QuizProcessor {

    // Display all quiz grades
    public static void displayQuizGrades(int[] quizzes) {
        System.out.println("Quiz grades:");
        for (int i = 0; i < quizzes.length; i++) {
            System.out.println("Quiz " + (i + 1) + ": " + quizzes[i]);
        }
    }

    // Display average of all quiz grades
    public static void displayAverage(int[] quizzes) {
        if (quizzes.length == 0) {
            System.out.println("No quiz grades to average.");
            return;
        }

        double sum = 0;
        for (int quiz : quizzes) {
            sum += quiz;
        }

        double average = sum / quizzes.length;
        System.out.printf("Quiz average: %.2f%n", average);
    }

    // Display average deleting lowest score
    public static void displayDroppedAverage(int[] quizzes) {
        if (quizzes.length <= 1) {
            System.out.println("Not enough quiz grades to drop lowest score.");
            return;
        }

        double sum = 0;
        int lowest = quizzes[0];
        // Find sum and lowest score in one loop
        for (int quiz : quizzes) {
            sum += quiz;
            if (quiz < lowest) {
                lowest = quiz;
            }
        }

        // Subtract lowest and calculate average
        double average = (sum - lowest) / (quizzes.length - 1);
        System.out.println("Average with lowest score dropped: " + average);
    }

    // Remove lowest score(s) from array
    public static int[] removeLowQuizScore(int[] quizzes) {
        if (quizzes.length == 0) {
            System.out.println("No quiz grades to remove.");
            return quizzes;
        }
        // Find lowest score
        int lowest = quizzes[0];
        for (int quiz : quizzes) {
            if (quiz < lowest) {
                lowest = quiz;
            }
        }
        // Count amount of lowest scores
        int count = 0;
        for (int quiz : quizzes) {
            if (quiz == lowest) {
                count++;
            }
        }
        // Create new array without lowest scores
        int[] temp = new int[quizzes.length - count];
        int index = 0;
        for (int quiz : quizzes) {
            if (quiz != lowest) {
                temp[index] = quiz;
                index++;
            }
        }

        System.out.println("Removed " + count + " occurrence(s) of lowest score: " + lowest);
        return temp;
    }

    // Test the methods
    public static void main(String[] args) {
        int[] quizzes = {85, 92, 78, 85, 90, 78, 95};

        System.out.println("Original grades:");
        displayQuizGrades(quizzes);

        displayAverage(quizzes);
        displayDroppedAverage(quizzes);

        removeLowQuizScore(quizzes);
        System.out.println("After removing lowest score(s):");
        displayQuizGrades(quizzes);

        quizzes = removeLowQuizScore(quizzes);
        System.out.println("After removing lowest score(s):");
        displayQuizGrades(quizzes);
    }
}