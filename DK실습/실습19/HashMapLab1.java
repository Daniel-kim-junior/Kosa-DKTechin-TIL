package day11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class HashMapLab1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String , Integer> hm = new HashMap<>();
        int total;
        String buffer;
        while (hm.size() != 5) {
            System.out.println("나라이름을 입력하세요 : ");
            buffer = br.readLine();
            System.out.println("인구 수를 입력하세요 : ");
            total = Integer.parseInt(br.readLine());
            if(hm.containsKey(buffer)) {
                System.out.println("*나라명 " + buffer + "는 이미 저장되었습니다.*");
            } else {
                hm.put(buffer, total);
                System.out.println("*저장되었습니다.*");
            }
        }
        System.out.println("5개가 모두 입력되었습니다");
        System.out.print("입력된 데이터 : ");
        StringBuilder sb = new StringBuilder();
        int cnt = 0;

        for(Map.Entry<String, Integer> map : hm.entrySet()) {
            cnt++;
            if(cnt != hm.size()) {
                sb.append(map.getKey() + "(" + map.getValue() + ") ,");
            } else {
                sb.append(map.getKey() + "(" + map.getValue() + ")");
            }
        }
        System.out.println(sb);
    }
}
