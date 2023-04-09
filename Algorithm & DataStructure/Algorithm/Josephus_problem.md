# 요세푸스 문제

N과 K가 주어졌을때

N명이 동그랗게 원을 그리고 앉고 K번째 자리에 있는 사람이 없어지는 수열

### 처음 내가 구현한 원형 큐를 이용한 방법

```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class mai {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ar[] = br.readLine().split(" ");
        int N = Integer.parseInt(ar[0]);
        int K = Integer.parseInt(ar[1]);

        cQueue<Integer> queue = new cQueue(N);
        for(int i = 1; i <= N; i++) {
            queue.insert(i);
        }
        int cnt = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        while(!queue.isEmpty()) {
            int i = queue.remove();
            if(cnt % K == 0) {
                if(queue.getLength() != 0) {
                    sb.append(i + ", ");
                } else {
                    sb.append(i);
                }
            } else {
                queue.insert(i);
            }
            cnt++;
        }
        sb.append(">");
        System.out.println(sb);


    }

    static class cQueue<T> {
        private int front = 0;
        private int rear = 0;
        private int maxSize;
        private Object[] queueArray;

        public cQueue(int maxSize) {
            this.maxSize = maxSize;
            queueArray = new Object[maxSize];
        }
        public int getLength() {
            return rear - front;
        }

        // 큐가 비어있는지 확인
        public boolean isEmpty() {
            // 인덱스가 처음이고 아무것도 없거나 인덱스가 0이 아닌경우
            return front == rear && queueArray[front] == null;
        }

        public boolean full() {
            return front == rear && queueArray[front] != null;
        }


        public void insert(T item) {
            if(full()) throw new ArrayIndexOutOfBoundsException();


            queueArray[rear++] = item;
            rear = rear % maxSize;
        }



        // 큐에서 front 데이터 제거
        public T remove(){

            if(isEmpty()) throw new ArrayIndexOutOfBoundsException();

            Object item = queueArray[front];
            queueArray[front++] = null;
            front = front % maxSize;

            return (T) item;
        }
    }

}

```

하지만 이 코드는 Queue에 insert하는 부분이 오버헤드가 있다.
insert하지말고 LinkedList로 모듈러 연산을 통해 O(1)로 Node를 제거해보자

```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Josephus_problem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ar[] = br.readLine().split(" ");
        int N = Integer.parseInt(ar[0]);
        int K = Integer.parseInt(ar[1]);

        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 1; i < N + 1; i++) {
            list.add(i);
        }
        int start = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        while(!list.isEmpty()) {
            start = (start + K - 1) % list.size();
            Integer remove = list.remove(start);
            if(!list.isEmpty()) {
                sb.append(remove + ", ");
            } else {
                sb.append(remove);
            }
        }
        sb.append(">");
        System.out.println(sb);

    }
}
```

엄청나게 시간복잡도가 줄었다
여기서 끝이 아닌 점화식을 이용하여 풀어보자

### 점화식을 이용한 풀이

```

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Josephus_problem {
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ar[] = br.readLine().split(" ");
        int N = Integer.parseInt(ar[0]);
        int K = Integer.parseInt(ar[1]);
        sb = new StringBuilder();
        LinkedList<Integer> arr = new LinkedList<>();
        for(int i = 1; i < N + 1; i++) {
            arr.add(i);
        }
        sb.append("<");
        int rst = 0;
        for(int i = 0; i < N; i++) {
            rst = (rst + K - 1) % (N - i);
            Integer remove = arr.remove(rst);
            if(i < N - 1) {
                sb.append(remove + ", ");
            } else {
                sb.append(remove);
            }
        }

        sb.append(">");
        System.out.println(sb);

    }

}
```

사실 다른건 없다 마지막 사람만 구하고 싶다면
LinkedList 사용 조차 필요없다.

s = ((s - K + 1) % n) + 1
점화식을 써서 구할 수 있다.

```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Josephus_problem {
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ar[] = br.readLine().split(" ");
        int N = Integer.parseInt(ar[0]);
        int K = Integer.parseInt(ar[1]);

        // 0 -> 1개일때 -> 2개일 때 -> N 개일 때...
        // 0 -> 1 -> K에 따라 start가 달라짐
        int start = 0;
        for(int i = 1; i <= N; i++) {
            start = ((start + K - 1) % i) + 1;
            System.out.println(start);
        }
    }

}
```

마지막 요세푸스 문제를 푸는 점화식 i == 1일 때 f(1) == 1을 이용하여
K에 따라 달라지는 시작점을 갱신해가며 마지막 사람의 인덱스를 구할 수 있다.
f(n) = (f(n - 1) + K - 1) % len(n)) + 1
DP는 아름답다
