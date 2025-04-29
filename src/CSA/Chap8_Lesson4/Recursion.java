package CSA.Chap8_Lesson4;
public class Recursion {
    public void test(int x){
        if(x>8){
            test(x-1);
        }
        System.out.println(x);
    }
    public int factorial(int n){
        if(n == 1){
            return 1;
        }
        return n * factorial(n-1);
    }
    public int fibonacci(int n){
        if(n == 3){
            return 1;
        }else if(n == 2){
            return 1;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }
    public static void main(String[] args) {
        Recursion recursion = new Recursion();
    }
}