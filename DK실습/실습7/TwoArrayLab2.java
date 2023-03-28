package exam;

import java.util.Arrays;

public class TwoArrayLab2 {
    public static void main(String[] args) {
        char arr[][] = {{'B', 'C', 'A', 'A'}, {'C','C','B','B'}, {'D','A','A','D'}};
        int intArray[] = new int[4];
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[0].length; j++) {
                intArray[arr[i][j] - 'A']++;
            }
        }

        for(int i = 0; i < intArray.length; i++) {
            System.out.printf("%s 는 %d개 입니다.\n", (char)(i + 65), intArray[i]);
        }

    }
}
