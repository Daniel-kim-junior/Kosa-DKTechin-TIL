package exam;

public class MethodLab5 {
    public static void main(String[] args) {
        String rst1 = "";
        String rst2 = "";
        for(int i = 0; i < 5; i++) {
            rst1 += getRandom(10);
            if(i < 4) {
                rst1 += ",";
            }
        }

        for(int i = 0; i < 5; i++) {
            rst2 += getRandom(10, 20);
            if(i < 4) {
                rst2 += ",";
            }
        }
        System.out.println(rst1);

        System.out.println(rst2);
    }

    public static int getRandom(int n) {
        return (int) (Math.random() * n) + 1;
    }
    public static int getRandom(int n1, int n2) {
        int r = Math.abs(n2 - n1) + 1;

        return n1 < n2 ? (int) (Math.random() * r) + n1 : (int) (Math.random() * r) + n2;


    }
}
