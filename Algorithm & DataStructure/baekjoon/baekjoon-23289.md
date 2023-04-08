```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    // 0 : 상, 1 : 하, 2 : 좌, 3 : 우
    static int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int MAP[][];
    static boolean walls[][][];
    static int R, C, K;
    static List<Node> checkList, warmList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        checkList = new ArrayList<>();
        warmList = new ArrayList<>();

        // 온도가 K 이상이면 테스트 중단
        MAP = new int[R + 1][C + 1];
        int tmp;
        for(int i = 1; i < R+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < C + 1; j++) {
                tmp = Integer.parseInt(st.nextToken());
                if(tmp > 0 && tmp < 5) {
                    // 온풍기 친구들
                    warmList.add(new Node(i, j, tmp));
                } else if(tmp == 5) {
                    // 조사해야할 칸들
                    checkList.add(new Node(i, j));
                }
            }
        }

        int W = Integer.parseInt(br.readLine());
        // 이 벽을 어떻게 관리하지?
        walls = new boolean[R + 1][C + 1][4];
        for(int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            if(t == 0) {
                // (x, y) , (x - 1, y)
                if(x > 1) {
                    walls[x][y][0] = true;
                    walls[x - 1][y][1] = true;
                }
            } else if(t == 1) {
                // (x , y), (x, y + 1)
                if(y < C) {
                    walls[x][y][3] = true;
                    walls[x][y + 1][2] = true;
                }
            }

        }
        int sumCheck = 0;
        int choco = 0;
        Node nd;
        while(sumCheck < K && choco < 101) {
            // 온풍기에서 바람 나옴
            for(int i = 0; i < warmList.size(); i++) {
                applyDevise(warmList.get(i));
            }
            // 온도 조절
            refresh();

            // 가장 바깥쪽 칸 온도 1감소
            downTemp();

            // 초콜렛 먹기
            choco++;

            // 체크
            if(isCheck()) {
                break;
            }
        }

        if(choco > 100) {
            System.out.println(101);
        } else {
            System.out.println(choco);
        }
    }
    static boolean isCheck(int K) {

    }
    static void downTemp() {

    }
    static void refresh() {

    }
    static void applyDevise(Node n) {
        int nx = n.x;
        int ny = n.y;

        while (nx > 0 && ny > 0 && nx < R + 1 && ny < C + 1) {

        }



    }
    static class Node {
        int x;
        int y;
        int dir;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }


}
```
