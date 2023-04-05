# 달이 차오른다, 가자

```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        res = -1;
        // map
        char MAP[][] = new char[N][];

        // 이진수를 저장할 visited
        // visited[][]의 값과 MinSik 객체의 값이 같으면 continue
        // 틀리면 비트 마스킹하고 queue offer
        int visited[][] = new int[N][M];

        // 민식이 큐
        Queue<MinSik> queue = new LinkedList<>();
        for(int i = 0; i < N; i++) {
            MAP[i] = br.readLine().toCharArray();
            for(int j = 0; j < M; j++) {
                if(MAP[i][j] == '0') {
                    queue.offer(new MinSik(i, j, 1, 0));
                }
            }
        }
        int nx, ny, key, k;
        MinSik minSik;
        while(!queue.isEmpty()) {
            minSik = queue.poll();

            // 민식이가 통로에 도착하면 return;
            if(MAP[minSik.x][minSik.y] == '1') {
                res = minSik.cnt;
                break;
            }
            for(int i = 0; i < 4; i++) {
                nx = minSik.x + dir[i][0];
                ny = minSik.y + dir[i][1];

                // 민식이가 얻은 키를 가지고 방문한 분기를 2진수로 저장해 놓은 visited[nx][ny]에 방문한 민식이의 2진수와 &연산하여 현재 민식이의 값과 다를때만 통과
                if(nx < 0 || ny < 0 || nx >= N || ny >= M || (visited[nx][ny] & minSik.bin) == minSik.bin || MAP[nx][ny] == '#') {
                    continue;
                }


                // 각각 다른 통로 키를 가지고 있는지 확인해야함
                if(MAP[nx][ny] >= 65 && MAP[nx][ny] < 71) {
                    // 마킹 확인 필
                    //  필요한 key는


                    // 2진키 추출
                    key = 2 << (MAP[nx][ny] - 65);


                    // 2진키와 민식이가 가진 키를 비교하여 키값과 다르면 키를 가지고 있지 않은것!!!
                    if((minSik.bin & key) != key) {
                        continue;
                    }

                    visited[nx][ny] = minSik.bin;
                    queue.offer(new MinSik(nx, ny, visited[nx][ny], minSik.cnt + 1));
                }

                if(MAP[nx][ny] == '.' || MAP[nx][ny] == '0') {
                    visited[nx][ny] = minSik.bin;
                    queue.offer(new MinSik(nx, ny, visited[nx][ny], minSik.cnt + 1));
                } else {
                  // 키 마킹 로직
                    k = 2 << (MAP[nx][ny] - 97);
                    
                    // 방문에 현재 민식이가 가지고있는 키에 + 방문 키 or 연산 
                    visited[nx][ny] = minSik.bin | k;

                    queue.offer(new MinSik(nx, ny, visited[nx][ny], minSik.cnt + 1));
                }
            }
        }
        System.out.println(res);
    }

    static class MinSik {
        int x;
        int y;
        int bin;
        int cnt;
        public MinSik(int x, int y, int bin, int cnt) {
            this.x = x;
            this.y = y;
            this.bin = bin;
            this.cnt = cnt;
        }
    }
}

```

비트 연산을 익힐 수 있는 좋은 문제 였다
|, &를 사용하여 2^32 즉 32개의 값까지는 비트 마스킹으로 공간 효율성 최적화를 할 수 있고
또 비트 시프트 << 를 통해 Math.pow() 함수를 생략해 시간 효율성도 최적화 할 수 있었다.
