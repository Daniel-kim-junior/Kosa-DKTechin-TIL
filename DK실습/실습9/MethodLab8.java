package exam;

import java.util.Arrays;

public class MethodLab8 {
    public static void main(String[] args) {
        System.out.printf("호출 1 : %d\n", addEven(10, 2, 5, 13, 7));
        System.out.printf("호출 2 : %d\n", addEven(11, 22, 33, 44, 55, 66));
        System.out.printf("호출 3 : %d\n", addEven());
        System.out.printf("호출 4 : %d\n", addEven(100, 101, 103));
    }

    public static int addEven(int... arr) {
        if(arr.length == 0) {
            return -1;
        }

       return Arrays.stream(arr).filter((e) -> e % 2 == 0).sum();
    }
}
