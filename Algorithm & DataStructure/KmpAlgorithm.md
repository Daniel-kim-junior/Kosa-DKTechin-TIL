# KMP Algorithm


- 문자열 비교 알고리즘


- Brute force 한 문자 검색은 O(n * m)

```
import java.io.*;

public class SimpleStringMatching {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s1 = br.readLine();
		String s2 = br.readLine();
		
		System.out.println(findString(s1,s2));
	}
	
	static int findString(String parent, String pattern) {
		int n1 = parent.length();
		int n2= pattern.length();
		for(int i=0; i<=n1-n2; i++) {
			boolean flag = true;
			for(int j=0; j<n2; j++) {
				if(parent.charAt(i+j) != pattern.charAt(j)) {
					flag = false;
					break;
				}
			}
			
			if(flag) {
				return 1; // 문자열을 찾으면 1
			}
		}
		return 0; // 찾지 못했으면 0 
	}
}
```


### 부분 문자열 검색 최적화 알고리즘 KMP

KMP는 접두사와 접미사의 개념을 활용하여 모든 경우를 계산하지 않고
*반복되는 연산을 줄여가 매칭 문자열을 빠르게 점프하는 기법

1. 접두사이면서 접미사인 부분 문자열 테이블을 생성 (부분 일치 테이블)

2. ex) abacaaba - a : 0, ab : 0, aba : 1, abac : 0, abaca: 1, abacaa: 1, abacaab: 2, abacaaba : 3
  - 연속된 글자가 같으면 idx++ , table[i]를 idx로 갱신(무조건 제일 긴 길이라서)
  - idx > 0일때 즉 같은 문자가 존재할때   



3. 생성된 테이블을 바탕으로
  - 경우의 수 3가지 
    1. 하나도 매칭되지 않았는데 match글자와 base 글자가 안맞을때? 시작점을 +1
    2. 매칭되었었지만 match글자와 base글자가 안맞을 때? 기록해 놓은 start += matched - table[i];
      지금까지 매칭된 개수와 최대 접두어를 뺀 idx로 start를 jumping
    3. 매치되었을때 matched += 1 해주고 pattern 길이와 비교
      - 길이가 같으면? return true
      - 길이가 같지않으면? continue
     
