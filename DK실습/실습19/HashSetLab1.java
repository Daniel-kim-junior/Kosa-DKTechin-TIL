package day11;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetLab1 {
    public static void main(String[] args) {
        HashSet<Integer> hs = new HashSet<>();
        while(hs.size() != 10) {
            hs.add(getRandom(21, 10));
        }
        StringBuilder sb = new StringBuilder();
        Iterator<Integer> it = hs.iterator();
        int cnt = 0;
        while (it.hasNext()) {
            cnt++;
            if(cnt != hs.size()) {
                sb.append(it.next() + ", ");
            } else {
                sb.append(it.next());
            }
        }
        System.out.print("오늘의 로또 번호 : ");
        System.out.println(sb);

    }

    static int getRandom(int m, int n) {
        return (int) (Math.random() * m) + n;
    }
}
