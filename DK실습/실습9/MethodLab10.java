package exam;

import java.util.stream.IntStream;

public class MethodLab10 {
    public static void main(String[] args) {
        int a1[] = new int[]{1 ,2, 3, 4, 5};
        MethodLab7.printArray(a1);
        powerArray(a1, 3);
        MethodLab7.printArray(a1);

        System.out.println();
        int a2[] = new int[]{10, 20, 30, 40, 50, 60};
        MethodLab7.printArray(a2);
        powerArray(a2, 10);
        MethodLab7.printArray(a2);
    }

    public static void powerArray(int[] arr, int n) {
        if(n < 2 || n > 5) {
            return;
        }
        IntStream.range(0, arr.length).forEach(i -> arr[i] *= n);

    }
}
