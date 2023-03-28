package exam;

import java.util.Scanner;

public class CalculatorTest {

    public static void main(String[] args) {

        try(Scanner sc = new Scanner(System.in)) {
            boolean flag = true;

            while(flag) {
                System.out.println("두 숫자를 입력해주세요");
                int x = sc.nextInt();
                int y = sc.nextInt();
                CalculatorExpr cal = new CalculatorExpr(x , y);
                System.out.println(cal);
                System.out.println("계속 하시겠습니까?");
                System.out.println("1. 계속,2.종료");
                int sel = sc.nextInt();
                if(sel == 2) flag = false;
                else if(sel != 1) System.out.println("잘못 입력하셨습니다.");
            }
            System.out.println("종료 되었습니다.");


        }
    }
}
