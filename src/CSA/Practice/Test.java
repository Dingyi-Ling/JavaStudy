package CSA.Practice;
import java.util.ArrayList;
public class Test{
    public static void main(String[] args){
        System.out.println(mystery(4));
    }
    public static int mystery(int n){
        if(n ==1){
            return 3;
        }else{
            return 3 * mystery(n-1);
        }
    }
}
