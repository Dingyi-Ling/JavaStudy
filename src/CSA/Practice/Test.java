package CSA.Practice;
import java.util.ArrayList;
public class Test{
    /**
     * The test bellow is from my questions to the 2024 CSA MCQ question 18, it is learned that when calling a function in the if statement
     * the print function in the function is still called, therefore the output is 223.
     */
//    public static int alpha(int x){
//        x = x * 2;
//        System.out.print(x);
//        return x + 1;
//    }
//    public static int beta(int y){
//        System.out.print(y);
//        return y + 6;
//    }
//    public static void main(String[] args){
//        int w = 1;
//        if(alpha(w) > 0 || beta(w) < 0){
//            System.out.print(alpha(w));
//        } else{
//            System.out.print(beta(w));
//        }
//    }
    public static void main(String[] args) {
        String test = "abcd";
        test = test.substring(5);
        System.out.println(test);
    }
}
