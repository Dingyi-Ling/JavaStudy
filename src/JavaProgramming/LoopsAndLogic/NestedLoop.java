package JavaProgramming.LoopsAndLogic;

public class NestedLoop {
    public static void main(String[] args){
        alaphabet();
        System.out.println();
        multiplicationTable();
        System.out.println();
        printPrime(100);
        System.out.println();
        printStars(5);
    }
    public static void alaphabet(){
        for (int i=0; i < 26; i++){
            System.out.print((char)(i+65));
            System.out.print((char)(i+97));
            if (i == 25){
                continue;
            }
            System.out.print(", ");
        }
    }
    public static void multiplicationTable(){
        for (int i = 1; i <= 10; i++){
            for (int j = 1; j <= 10; j++){
                System.out.printf("%4d", i*j);
            }
            System.out.println();
        }
    }
    public static void printPrime(int x){
        for (int i = 2; i <= x; i++){
            boolean isPrime = true;
            for (int j = 2; j < i; j++){
                if (i % j == 0){
                    isPrime = false;
                    break;
                }
            }
            if (isPrime){
                System.out.printf("%4d", i);
            }
        }
    }
    public static void printStars(int n){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == i || j == (n - 1 - i)) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
