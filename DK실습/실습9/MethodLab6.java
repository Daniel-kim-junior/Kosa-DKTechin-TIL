package exam;

import java.util.Arrays;

public class MethodLab6 {
    public static void main(String[] args) {
        int arr1[] = new int[]{10, 20, 30};
        int arr2[] = new int[]{100, 500, 300, 200, 400};
        int arr3[] = new int[]{1, 10, 3, 4, 5, 8, 7, 6, 9, 2};

        System.out.printf("가장 큰 값은 %d 입니다.\n", maxNumArray(arr1));
        System.out.printf("가장 큰 값은 %d 입니다.\n", maxNumArray(arr2));
        System.out.printf("가장 큰 값은 %d 입니다.\n", maxNumArray(arr3));

    }

    public static int maxNumArray(int[] arr) {
        return Arrays.stream(arr).max().orElse(0);
    }
}
