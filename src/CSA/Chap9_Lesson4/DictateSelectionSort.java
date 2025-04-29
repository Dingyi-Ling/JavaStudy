package CSA.Chap9_Lesson4;

public class DictateSelectionSort {
    public static int[] selectionSort(int[] arr){
        for(int i=0; i < arr.length; i++){
            for(int j = i+1; j < arr.length; j++){
                if(arr[i] >= arr[j]){
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        return arr;
    }
    public static void main(String[] args) {
        DictateSelectionSort ds = new DictateSelectionSort();
        int[] arr = {4,2,6,2,6,1,8,4,3};
        ds.selectionSort(arr);
        for(int i=0; i<arr.length; i++){
            System.out.println(arr[i]);
        }
    }
}
