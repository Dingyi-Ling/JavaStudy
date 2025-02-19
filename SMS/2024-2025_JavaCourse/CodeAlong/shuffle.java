public class shuffle {
    public static void main(String Args[]){
        int[] array = new int[10];
        array = shuffle(array);
        for(int num: array){
            System.out.println(num);
        }
    }
    public static int[] shuffle(int[] array){
        for(int i = 0; i < array.length; i++){
            int randomIndex = (int)(Math.random() * array.length);
            int temp = array[i];
            array[i] = array[randomIndex];
            array[randomIndex] = temp;
        }
        return array;
    }
}
