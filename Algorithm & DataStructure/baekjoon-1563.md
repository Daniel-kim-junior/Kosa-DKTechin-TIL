# 개근상
```
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;

public class Main {
        static int DP[][][];
        static int N;
        static final int MOD = 1000000;
        public static void main(String[] args) throws IOException {
            // N은 1000
            // % 1000000으로 나눈 나머지를 출력
            // 개근상을 받을 수 있는 경우의 출력
            // O 출석, L 지각, A 결석

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            if(N == 1) {
                System.out.println(3);
                return;
            }
            DP = new int[N + 1][3][4];

            for(int i = 0; i < N + 1; i++) {
                for(int j = 0; j < 3; j++) {
                    for(int k = 0; k < 4; k++) {
                        DP[i][j][k] = -1;
                    }
                }
            }


            // N - 1 O일때 - (N - 1) 번째 까지 뭘했어도 상관없음 (지각 초기화)
            // N - 1 L일때 - (N - 1) 번째 까지는 L을 1번 했으면 안됨 (지각 초기화)
            // N - 1 A일때 - (N - 1) 번째 까지 연속으로 A를 2번 했으면 안됨

            int rst = 0;

            rst = (rst +cal(1 , 0, 0)) % MOD;
            rst = (rst +cal(1 , 1, 0)) % MOD;
            rst = (rst +cal(1 , 0, 1)) % MOD;
            System.out.println(rst);


        }

        static int cal(int n, int jigak, int out) {
            if(jigak == 2) {
                return 0;
            }
            if(out == 3) {
                return 0;
            }

            if(n == N) {
                return 1;
            }

            //============================//
            // 중요 부분
            if(DP[n][jigak][out] != -1) {
                return DP[n][jigak][out];
            }
            //============================//
            int rst = 0;
            rst = (rst + cal(n + 1, jigak, out + 1)) % MOD;
            rst = (rst + cal(n + 1, jigak + 1, 0)) % MOD;
            rst = (rst + cal(n + 1, jigak, 0)) % MOD;

            DP[n][jigak][out] = rst;
            return rst;
        }
    }

```
중요 부분 즉 메모이제이션 기법을 쓰지않으면 시간초과가 나는 코드이다.
재귀를 쓰면 중복 계산이 일어나는데 어떤 시점의 함수 호출이 중복해서 일어난다.
이 문제는 N번까지 갈 수 있는지 없는지 확인하는 문제인데
현재 단계까지 경우의 수 계산을 완료했다면 다시 계산할 필요가 없는것이
포인트다.

DP를 -1로 초기화 하는 이유 경우의 수가 0인 경우와 구분하기 위한 초기화였다

