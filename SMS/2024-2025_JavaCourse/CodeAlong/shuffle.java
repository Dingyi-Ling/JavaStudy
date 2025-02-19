public class shuffle {
    public static void main(String Args[]){
        int[] array = new int[10];
        for(int i = 0; i < array.length; i++){
            array[i] = i;
        }
        for(int num: array){
            System.out.print(num + " ");
        }
        //shuffle the array using swapping
        for(int i = 0; i < array.length; i++){
            int randomIndex = (int)(Math.random() * array.length);
            int temp = array[i];
            array[i] = array[randomIndex];
            array[randomIndex] = temp;
        }
        System.out.println();
        for(int num: array){
            System.out.print(num + " ");
        }

    }
}
