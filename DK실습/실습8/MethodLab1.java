package exam;

import java.text.DecimalFormat;

public class MethodLab1 {
    public static void main(String[] args) {
        DecimalFormat decmicalFormat = new DecimalFormat("###,###");

        System.out.print("우리의 자바교재명은");
        System.out.print("<"); printTitle();
        System.out.print(">");
        System.out.println("입니다.");
        System.out.printf("가격은 %s원입니다.", decmicalFormat.format(getPrice()));
    }

    static void printTitle() {
        System.out.print("이것이 자바다");
    }

    static int getPrice() {
        return 36000;
    }
}
