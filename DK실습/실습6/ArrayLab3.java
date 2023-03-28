import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayLab3 {
    public static void main(String[] args) {
        char javaChar[] = {'J', 'a', 'v', 'a'};

        IntStream.range(0, javaChar.length).forEach(e -> {
            if(javaChar[e] < 97) {
                javaChar[e] = (char) (javaChar[e] + 32);
            } else {
                javaChar[e] = (char) (javaChar[e] - 32);
            }
        });

        IntStream.range(0, javaChar.length).forEach(e -> System.out.println(javaChar[e]));

    }
}
