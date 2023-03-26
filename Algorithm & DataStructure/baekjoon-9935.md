### 문자열 폭발 (Stack)
```
import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.util.LinkedList;  
import java.util.stream.Collectors;  
  
public class Main {  
    public static void main(String[] args) throws IOException {  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
        String s = br.readLine();  
        String bomb = br.readLine();  
        int bLen = bomb.length();  
        StringBuilder sb = new StringBuilder();  
  
  
        for(int i = 0; i < s.length(); i++) {  
            sb.append(s.charAt(i));  
            if(sb.length() >= bLen) {  
                boolean flag = true;  
                int l = bLen - 1;  
                for(int j = sb.length() - 1; j >= sb.length() - bLen; j--) {  
                    if(sb.charAt(j) != bomb.charAt(l)) {  
                        flag = false;  
                        break;                    }  
                    l--;  
                }  
                if(flag) {  
                    sb = sb.replace(sb.length() -  bLen, sb.length(), "");  
                }  
            }  
        }  
  
        if(sb.toString().equals("")) {  
            System.out.println("FRULA");  
        } else {  
            System.out.println(sb);  
        }  
    }  
  
  
}
```

이 문제는 toString()을 매번 호출하면 문자열 객체가 계속 생성된다는 사실을 상기 시킬 수 있었던 문제였다

추상화 되어 있는 메소드를 사용할 때 항상 주의해서 메소드의 사양을 잘 읽고 코드를 짜야겠다는 생각을 했다.

StringBuilder를 사용하여 문자 객체 생성을 최소화했다.
