# 타일 채우기

```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long p = cal(N);
        System.out.println(p);
    }
    public static long cal(int N) {
        if(N == 0) {
            return 1;
        }
        if(N == 1) {
            return 0;
        }
        if(N == 2) {
            return 3;
        }

        long rst = cal(N - 2) * 3;
        for(int i = 3; i <= N; i++) {
            if(i % 2 == 0) {
                rst += cal(N - i) * 2;
            }
        }
        return rst;
    }
}

```
3 * N 크기의 벽을 2 * 1, 1* 2 크기의 타일로 채우는 경우의 수를 구하는 문제

DP문제이다. DP는 부분문제를 잘 나누어 생각하는 것이 좋은것 같다.
N 번째 문제를 해결하기 위해 N - 1, N - x 번째 크기에서 공통되는 부분을 구하고
그 후의 규칙또한 생각해보아야 한다는 것을 배운 문제
여기서는 N을 만들기 위해 N - 2 번째 개수를 3배해주는 공통분모가 있었으며
홀수번째에는 벽돌을 만들 수 없기 때문에 제외하고
짝수 번째에 2개씩 새로운 벽돌 배열을 추가시켜주어야 하는 문제였다.
굉장히 많은 것을 생각하게 해준 문제다.



