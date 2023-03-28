package exam;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class GradeTest {
    public static void main(String[] args) {

        try(Scanner sc = new Scanner(System.in)) {
            System.out.println("입력할 점수들의 개수를 입력해주세요");
            int n = sc.nextInt();
            int arr[] = new int[n];
            System.out.println(n + "개의 숫자를 입력해주세요");
            IntStream.range(0, n).forEach(i -> arr[i] = sc.nextInt());
            GradeExpr ge = new GradeExpr(arr);
            String rst = "";
            System.out.print("점수들 : ");
            for(int i = 0; i < arr.length - 1; i++) {
                rst += arr[i] + ",";
            }
            rst += arr[arr.length - 1];
            System.out.println(rst);
            System.out.println(ge);

        }

    }
}
