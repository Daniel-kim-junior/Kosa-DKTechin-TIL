# 입국심사

```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static long prod[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);


        prod = new long[n];
//       입국 심사대 입력
        long min = Long.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            prod[i] = Integer.parseInt(br.readLine());
            if(min > prod[i]) {
                min = prod[i];
            }
        }

        if(n == 1) {
            System.out.println(m * prod[0]);
            return;
        }



        // 1. 심사대 정렬
        long left = 1;
        long right = min * m;
        long cnt;
        while(left < right) {
            cnt = 0L;
//          최소 시간일때
            long mid = (left + right) / 2;

            for(int i = 0; i < n; i++) {
                cnt += mid / prod[i];
            }
            if(cnt < m) {
                left = mid + 1;
            } else {
                right = mid;
            }

        }

        System.out.println(left);

    }


}
```


이 문제는 심사대의 입장에서 고정 시간당 몇명의 인원을 쳐낼 수 있는가에 대한 문제였다.

예를 들면 20초라는 시간이 있다면
1번 검사대는 7초
2번 검사대는 10초
라는 시간이 걸린다면 20초동안 1번 검사대는 2명, 2번 검사대도 2명을 쳐낼 수 있어서 4명을 처리할 수 있다.
최대 4명을 처리할 수 있는데 만약 심사를 받고자 하는 사람이 3명이라면
이분탐색으로 최소시간을 구하기 위해 MID시간을 구해서
시간당 처리할 수 있는 인원의 수를 구해 인원수가 구하고자 하는 사람 수보다 작으면 시간을 늘려주고 아니면 시간을 줄여줬다.