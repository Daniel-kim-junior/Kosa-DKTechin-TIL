# 백준 뒤집기

```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
   static int dir[][] = {{0, 1}, {0, -1}, {-1, 0} ,{1, 0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int arr[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];
        String s;
        char MAP[][] = new char[n][m];
        for(int i = 0; i < n; i++) {
            s = br.readLine();
            for(int j = 0; j < m; j++) {
                MAP[i][j] = s.charAt(j);
            }
        }
        int count = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                for(int k = 0; k < 4; k++) {
                    int nx = i + dir[k][0];
                    int ny = j + dir[k][1];
                    if(nx >= 0 && nx < n && ny >= 0 && ny < m) {
                        if(MAP[nx][ny] != MAP[i][j]) {
                            count++;
                            break;
                        }
                    }

                }
            }
        }


        long rst = solution(m * n - count);
        System.out.println(rst % 1000000007);

    }

    public static long solution(long n) {
        if(n == 1) {
            return 2;
        }

        long rst = solution(n / 2);

        rst = (rst * rst) % 1000000007;


        if(n % 2 == 0) {
            return rst;
        }

        return rst * 2;
    }
}
```


![image](https://user-images.githubusercontent.com/67178562/228842293-b1f46ddc-f98e-478e-a2df-74e26ba9b5f3.png)

최종 발판으로 수렴할 수 있는 경우의 수는 2 ^ (M * N) 개이다. 하지만 누를 수 없는 한번의 경우의 수만 가진 발판들이 있고 그 발판들을 세어 빼주어야 한다. 

하나의 발판을 누르게 된다면 색은 반전되는데 변을 공유하는 주변 발판도 같이 색이 반전되는 것이 이번 문제의 핵심이였다.

전염되는 조건은 '같은 색'이기 때문에 그림과 같은 발판들은 절대 마지막 발판으로 수렴할 수 없다.

반면에 3번째 벽돌은 두 가지 경우의 수가 나올 수 있는 누를 수 있는 발판들이다.

따라서 마주한 변의 색이 다르면 그 발판은 누를 수 없는 변이 되고 경우의 수 1개를 빼주어야 하는 발판이다.

답은 2 ^ (M * N - (누를 수 없는 발판 개수))이다.

자바에서 아주 큰 수의 제곱은 OverFlow되기 때문에 분할 정복으로 제곱을 해결 해 주었다. 



