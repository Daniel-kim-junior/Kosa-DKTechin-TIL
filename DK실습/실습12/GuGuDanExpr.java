package exam2;

public class GuGuDanExpr extends Multiplication {
    GuGuDanExpr() {
        super();
    }

    GuGuDanExpr(int dan) {
        super(dan);
    }

    GuGuDanExpr(int dan, int number) {
        super(dan, number);
    }


    public static void printAll() {
        for(int i = 1; i <= 9; i++) {
            for(int j = 1; j <= 9; j++) {
                System.out.printf("%d * %d = %d\t", i , j , i * j);
            }
            System.out.println();
        }
    }
}
