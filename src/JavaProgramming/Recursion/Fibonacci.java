package JavaProgramming.Recursion;

public class Fibonacci {
    private int length;
    public Fibonacci(int length) {
        this.length = length;


    }
    public int GetLength(){
        return length;
    }
    public void SetLength(int length){
        this.length = length;
    }
    public int GetSequence() {
        return GetSequenceHelper(this.length);
    }

    private int GetSequenceHelper(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return GetSequenceHelper(n - 2) + GetSequenceHelper(n - 1);
    }
    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci(10);
        System.out.println(fib.GetSequence());
        int i = (int)(Math.random() * 10 + 1);
    }
}
