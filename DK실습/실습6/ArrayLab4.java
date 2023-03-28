import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayLab4 {
    public static void main(String[] args) {

        int rand = ArrayLab2.makeRandom(6, 5);
        int intArray[] = IntStream.range(0, rand).map(e -> ArrayLab2.makeRandom(26, 1)).toArray();
        int[] charArray = Arrays.stream(intArray).map(e -> (char) (e + 96)).toArray();

        Arrays.stream(intArray).forEach(e -> {
            System.out.print(e);
            if(e == intArray[rand - 1]) {
                System.out.println();
            } else {
                System.out.print(", ");
            }
        });
        Arrays.stream(charArray).forEach(e -> {
            System.out.print((char) e);
            if(e != charArray[rand - 1]) {
                System.out.print(", ");
            }
        });


    }
}
