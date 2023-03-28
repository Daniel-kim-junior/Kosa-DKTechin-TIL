package exam2;

public class GuGuDanApp {
    public static void main(String[] args) {
        int dan = getRandom(20, 1);
        int number = getRandom(20, 1);
        GuGuDanExpr guGuDanExpr;
        if(dan < 10 && number < 10) {
            guGuDanExpr = new GuGuDanExpr(dan, number);
            System.out.printf("%d * %d = ", dan, number);
            guGuDanExpr.printPart();
        } else if(dan < 10 && number >= 10) {
            guGuDanExpr = new GuGuDanExpr(dan);
            System.out.printf("%d단 : ", dan);
            guGuDanExpr.printPart();
        } else {
            GuGuDanExpr.printAll();
        }
    }


    public static int getRandom(int n , int m) {
        return (int) (Math.random() * n) + m;
    }
}
