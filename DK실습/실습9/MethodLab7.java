package exam;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MethodLab7 {
    public static void main(String[] args) {
        IntStream.range(2, 5).forEach(i -> printArray(powerArray(i)));
    }

    public static int[] powerArray(int x) {
        int arr[] = new int[10];

        IntStream.range(0, 10).forEach(i -> arr[i] = (i + 1) * x);
        return arr;
    }
    public static void printArray(int[] arr) {
        IntStream.range(0, arr.length - 1).forEach(e -> System.out.print(arr[e] +","));
        System.out.println(arr[arr.length - 1]);

    }
}
