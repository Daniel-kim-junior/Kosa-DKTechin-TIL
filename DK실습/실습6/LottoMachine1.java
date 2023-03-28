import java.util.stream.IntStream;

public class LottoMachine1 {
    public static void main(String[] args) {
        System.out.print("오늘의 로또 번호 - ");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 6; i++) {
            sb.append(ArrayLab2.makeRandom(45, 1));
            if(i != 5) {
                sb.append(",");
            }
        }
        System.out.println(sb);

    }
}
