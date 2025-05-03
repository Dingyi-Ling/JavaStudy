package JavaProgramming.ArrayLists;

import java.util.ArrayList;
import java.util.Arrays;
public class ArrayListWarmUp{
    public static ArrayList<Character> deleteOdd(ArrayList<Character> list){
        ArrayList<Character> returnList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            if (i % 2 != 0){
                returnList.add(list.get(i));
            }
        }

        return returnList;
    }

    public static ArrayList<String> reverseString(ArrayList<String> list){
        ArrayList<String> returnList = new ArrayList<>();
        for(int i = list.size() - 1; i >= 0 ; i--){
            returnList.add(list.get(i));
        }
        return returnList;
    }

    public static ArrayList<Double> doubleDoubleList(ArrayList<Double> list){
        list.replaceAll(aDouble -> aDouble * 2);
        return list;
    }

    public static ArrayList<Character> specialAlphabet(int n){
        ArrayList<Character> list = new ArrayList<>();
        for(int i = 0; i < n; i ++){
            if(i % 2 == 0){
                list.addFirst((char)(i + 97));
            } else {
                list.add((char)(i + 97));
            }
        }
        return list;
    }
    public static void main(String[] Args){
        ArrayList<Character> characterList = new ArrayList<>(Arrays.asList('d', 'k', 'e', 'p', 'w', 's', 'f'));
        ArrayList<String> stringList = new ArrayList<>(Arrays.asList("lists", "are", "great"));
        ArrayList<Double> doubleList = new ArrayList<>(Arrays.asList(10.12, 23.12, 78.23, 89.23, 235.56));
        System.out.println(deleteOdd(characterList));
        System.out.println(reverseString(stringList));
        System.out.println(doubleDoubleList(doubleList));
        System.out.println(specialAlphabet(5));
    }
}