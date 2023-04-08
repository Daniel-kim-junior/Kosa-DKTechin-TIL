# 홍준이의 행렬

```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long A[] = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }
        long B[] = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            B[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(A);
        Arrays.sort(B);

        // 최소 범위
        long left = A[0] * B[0];

        // 최대 범위
        long right = A[N - 1] * B[N - 1];

        // 이분 탐색
        while(left < right) {
            long mid = (left + right) / 2;
            long cnt = 0;
            // A 탐색
            for(int i = 0; i < N; i++) {
                int l = 0;
                int r = N - 1;
                // B 선택 mid값 보다 작거나 같은 (Higher bound)
                while(l <= r) {
                    int m = (l + r) / 2;
                    if(A[i] * B[m] <= mid) {
                        l = m + 1;
                    } else {
                        r = m - 1;
                    }
                }
                // length를 더해준다.
                cnt += l;
            }

            // length가 K 보다 작으면 최소값 up
            if(cnt < K) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        System.out.println(left);

    }
}

```


이분탐색을 두 번 구현한 문제 10억 이하의 자연수라 long으로 설정

이분 탐색의 범위를 설정하는 것이 가장 중요하고 lower-bound 기법을 선택할 것인지

higher-bouynd 기법을 선택할 것인지 잘 생각해야 한다.

반드시 for-loop을 해야하는지 잘 고민하자.