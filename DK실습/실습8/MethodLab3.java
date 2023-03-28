package exam;

public class MethodLab3 {
    public static void main(String[] args) {
        if(isEven(10)) {
            System.out.println("10은 짝수입니다.");
        }

        if(!isEven(13)) {
            System.out.println("13은 홀수입니다.");
        }

    }
    static boolean isEven(int num) {
        return num % 2 == 0;
    }
}
