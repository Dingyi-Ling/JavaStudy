package CSA.Practice;
import java.util.ArrayList;
public class Test {
    public static void main(String[] args) {
        int[][] mat = {{3,4,5},
                {6,7,8}};
        int sum = 0;
        for (int[] arr: mat)
        {
            for (int n = 0; n < mat.length; n++){
                sum += arr[n];
                System.out.println(arr[n]);
            }

        }
        System.out.println(sum);
    }
}
