package exam;

public class Methodlab4 {
    public static void main(String[] args) {
        int rand1;
        int rand2;
        for(int i = 0; i < 5; i ++ ) {
            rand1 = (int) (Math.random() * 30) + 1;
            rand2 = (int) (Math.random() * 30) + 1;
            System.out.printf("%d 와 %d 의 차는 %d 입니다.\n", rand1, rand2, makeMethod(rand1, rand2));

        }
    }

    static int makeMethod(int x, int y) {
        if(x == y) {
            return 0;
        }

        return x > y ? x - y : y - x;

    }
}
