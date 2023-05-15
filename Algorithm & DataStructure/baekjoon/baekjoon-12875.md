# 백준-12875 칙령

총 N명이 있고 돈은 음이 아닌 정수이다.
사람들은 1번부터 N번까지 번호가 매겨져 있다.
모든 사람이 가지고 있는 돈은 자신의 친구가 가지고 있는 돈과 최대 d원 만큼 차이가 나야 한다.

즉 자신의 친구와는 최대 d만큼 차이날 수 있어서 친구의 연결 Depth가 길면 길수록 최대값이 늘어나게 된다.
따라서 가장 긴 거리 \* d가 답이된다.

문제를 이해하는데 오래 걸린 문제

### 플로이드 와샬

- 모든 정점에서 모든 정점으로 최단 거리를 구하는 알고리즘
  이 문제에서는 특정 정점에서 특정 정점으로의 모든 거리를 구한 후
  이어져 있는 즉 친구 관계가 없다면 무한대 -1을 출력하고
  이어져 있다면 (친구 관계의 Depth가 가장 큰 최단거리) \* d를 출력한다

```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int d = Integer.parseInt(br.readLine()); // 최대 차이 값
        int[][] dist = new int[N + 1][N + 1];

        int INF = 1000000000;
        for(int i = 1; i < N+1; i++) {
            for(int j = 1; j < N+1; j++) {
                if(i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = INF;
                }
            }
        }

        // 인접 행렬 초기화
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                if (s.charAt(j) == 'Y') {
                    dist[i + 1][j + 1] = 1;
                }
            }
        }




        for(int i = 1; i < N+1; i++) {
            for(int j = 1; j < N+1; j++) {
                for(int k = 1; k < N+1; k++) {
                    dist[j][k] = Math.min(dist[j][k], dist[j][i] + dist[i][k]);
                }
            }
        }
        int maxD = 0;
        for(int i = 1; i < N+1; i++) {
            for(int j = 1; j < N+1; j++) {
                maxD = Math.max(maxD, dist[i][j]);
            }
        }

        maxD = maxD == INF ? -1 : maxD * d;
        System.out.println(maxD);

    }
}

```
