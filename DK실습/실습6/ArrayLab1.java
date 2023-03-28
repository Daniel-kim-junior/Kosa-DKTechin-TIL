import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ArrayLab1 {
    public static void main(String[] args) {
        int ary[]= new int[10];

        IntStream.range(0, ary.length).forEach(i -> {
            System.out.print(ary[i] + " ");
            if (i == ary.length - 1) System.out.println();
        });
        int mapArray[] = IntStream.range(0, ary.length).map(i -> (i + 1) * 10).toArray();

        OptionalInt firstNum = Arrays.stream(mapArray).findFirst();
        OptionalInt lastNum = Arrays.stream(mapArray).reduce((first, second) -> second);
        firstNum.ifPresent(value -> System.out.println("첫 번째 : " + value));
        lastNum.ifPresent(value -> System.out.println("마지막 : " + value));

        int sum = firstNum.orElse(0) + lastNum.orElse(0);
        System.out.println("합 : " + sum);


        String arrayString = Arrays.stream(mapArray).mapToObj(Integer::toString).collect(Collectors.joining(" "));
        System.out.println(arrayString);

        IntStream.of(mapArray).boxed().sorted(Collections.reverseOrder()).forEach(e -> System.out.print(e + " "));
        System.out.println();
        IntStream.range(0, ary.length).forEach(i -> {
            if(i % 2 == 1) System.out.print(mapArray[i] + " ");
        });
    }
}
