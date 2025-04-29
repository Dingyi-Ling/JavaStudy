package JavaProgramming.Strings;

public class StringWarmUp {
    public static void PrintHalf(String str){
        System.out.println(str.substring(str.length()/2));
    }
    public static String Concatenate(String str1, String str2){
        return str1.substring(1,str1.length()) + str2.substring(1,str2.length());
    }
    public static Boolean IfAdverb(String str){
        return str.substring(str.length()-2,str.length()).equals("ly");
    }
    public static Boolean Contains(String str1, String str2) {
        return str1.indexOf(str2) != -1;
    }
    public static void PrintBackwards(String str) {
        String reversed = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed += str.charAt(i);
        }
        System.out.println(reversed);
    }
    public static void main(String[] args){
        PrintHalf("Method");
        PrintHalf("hi there");
        System.out.println(Concatenate("hello", "there"));
        System.out.println(IfAdverb("fast"));
        System.out.println(IfAdverb("happily"));
        System.out.println(Contains("hippo","hi"));
        System.out.println(Contains("hippo","hit"));
        PrintBackwards("hello");
    }
}
