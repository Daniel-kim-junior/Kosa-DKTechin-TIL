package exam;

public class CalculatorExpr {
    private int num1;
    private int num2;


    CalculatorExpr(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }


    int getAddition() {
        return num1 + num2;
    }

    int getSubtraction() {
        return num1 - num2;
    }

    int getMultiplication() {
        return num1 * num2;
    }

    double getDivision() {
        return num1 / num2;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }


    public void setNum2(int num2) {
        this.num2 = num2;
    }

    @Override
    public String toString() {
        return "덧셈 : " + getAddition() + "\n" +
                "뺄셈 : " + getSubtraction() + "\n" +
                "곱셈 : " + getMultiplication() + "\n" +
                "나눗셈 : " + getDivision() + "\n";

    }
}
