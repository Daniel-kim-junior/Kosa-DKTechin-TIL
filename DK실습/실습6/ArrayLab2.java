import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayLab2 {
    public static void main(String[] args) {
        int array[] = new int[10];

        int randArray[] = IntStream.range(0, array.length).map(i -> i = makeRandom(17, 4)).toArray();
        System.out.print("모든 원소의 값 : " );
        IntStream.range(0, array.length).forEach(i -> {
            System.out.print(randArray[i]);
            if(i < array.length - 1) {
                System.out.print(",");
            } else {
                System.out.println();
            }
        });

        int randSum = Arrays.stream(randArray).sum();
        System.out.println("모든 원소의 합 : " + randSum);


    }
    static int makeRandom(int x, int n) {
        return (int) (Math.random() * x) + n;
    }
}
