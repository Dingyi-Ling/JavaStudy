package JavaProgramming.ArraysAndObjects;

public class Shuffle {
    public static void main(String Args[]){
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++){
            array[i] = i;
        }
        array = shuffleArray(array);
        for(int num: array){
            System.out.print(num);
        }
    }
    public static int[] shuffleArray(int[] array){
        for(int i = 0; i < array.length; i++){
            int randomIndex = (int)(Math.random() * array.length);
            int temp = array[i];
            array[i] = array[randomIndex];
            array[randomIndex] = temp;
        }
        return array;
    }
}