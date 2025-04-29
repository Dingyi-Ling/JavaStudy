package JavaProgramming.InputsAndOutputs;

import java.util.Scanner;
public class Mad_Lib {
    public static void main(String[] args) {
        //Dingyi Part starts
        @SuppressWarnings("resource")
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the name of a Place");
        String Place1 = keyboard.nextLine();

        System.out.println("Enter a Noun");
        String Noun1 = keyboard.nextLine();

        System.out.println("Enter a Adjective");
        String Adjective1 = keyboard.nextLine();

        System.out.println("Enter a Adjective");
        String Adjective2 = keyboard.nextLine();

        System.out.println("Enter a Noun");
        String Noun2 = keyboard.nextLine();

        System.out.println("Enter a Body Part");
        String BodyPart1 = keyboard.nextLine();

        System.out.println("Enter a name");
        String Name1 = keyboard.nextLine();

        System.out.println("Enter= a Noun");
        String Noun3 = keyboard.nextLine();

        System.out.println("Enter a Plurnal noun");
        String Plurnal_Noun1 = keyboard.nextLine();

        System.out.println("Enter a past tense verb ");
        String Verb1 = keyboard.nextLine();

        System.out.println("Enter a Noun");
        String Noun4 = keyboard.nextLine();
        //Dingyi Part Ends
        //Nikhil Parts Starts
        System.out.println("Enter a name");
        String Name2 = keyboard.nextLine();

        System.out.println("Enter the name of a Place");
        String Place2 = keyboard.nextLine();

        System.out.println("Enter a Noun");
        String Noun5 = keyboard.nextLine();

        System.out.println("Enter a Noun");
        String Noun6 = keyboard.nextLine();

        System.out.println("Enter a Adverb");
        String Adverb = keyboard.nextLine();

        System.out.println("Enter two letters");
        String Ly = keyboard.nextLine();

        System.out.println("Enter a Noun");
        String Noun7 = keyboard.nextLine();

        System.out.println("Enter a Plural Noun");
        String Plural_Noun2 = keyboard.nextLine();

        System.out.println("Enter a Verb ending with a s");
        String Verb2 = keyboard.nextLine();

        System.out.println("Enter a name of a food");   
        String Noun_Food = keyboard.nextLine();

        System.out.println("Enter a feeling");
        String Feeling1 = keyboard.nextLine();

        System.out.println("Enter a action");
        String Action1 = keyboard.nextLine();

        System.out.println("Enter a Bodypart");
        String BodyPart2 = keyboard.nextLine();
        //Nikhil Part Ends
        //Dingyi Part Starts
        System.out.println("A great word game to play in the " + Place1 + " is Mad " + Noun1 + "."); // not really a use of a escape character but I do understand it is the black slashes
        System.out.println("Here are the " + Adjective1 + " " + Adjective2 + " instructions.");
        System.out.println("A reader has a Mad " + Noun2 + " book in one " + BodyPart1 + " and a pencil or pen in the other.");
        System.out.println("They ask each " + Name1 + " in the car for a part of " + Noun3 + " (noun, verb, adjective, or adverb) to fill in the " + Plurnal_Noun1 + ".");
        System.out.println("In case you’ve " + Verb1 + ", an adjective is a " + Noun4 + " used to modify something or someone.");
        //Dingyi Part Ends
        //Nikhil Part Starts
        System.out.println("A noun is the name of a " + Name2 + ", " + Place2 + ", or " + Noun5 + ".");
        System.out.println("A verb is an action " + Noun6 + ".");
        System.out.println("An " + Adverb + " usually ends in the letters " + Ly + " and modifies a(n) " + Noun7 + ".");
        System.out.println("When all the " + Plural_Noun2 + " are filled in, the reader " + Verb2 + " the finished " + Noun_Food + " aloud.");
        System.out.println("The completed " + Noun_Food + " is usually greeted with shrieks of " + Feeling1 + ", hoots of " + Action1 + ", and much " + BodyPart2 + " clapping.");
        //Nikhil Part Ends
    }
}
