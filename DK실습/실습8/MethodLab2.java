package exam;

public class MethodLab2 {
    public static void main(String[] args) {
        printMethod('A', 5);
    }
    static void printMethod(char c, int n) {

        for(int i = 0; i < n; i++) {
                for(int j = n - i - 1; j > 0; j-- ) {
                    System.out.print(" ");
                }
                for(int j = 0; j < i + 1; j++) {
                    System.out.print(c);
                }


            System.out.println();
        }
    }
}
