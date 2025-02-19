public class shuffle {
	public static void main(String Args[]){
        int[] array = new int[10];
        for(int i = 0; i < array.length; i++){
            array[i] = i;
        }
        for(int num: array){
            System.out.print(num + " ");
        }
        
    }
}
