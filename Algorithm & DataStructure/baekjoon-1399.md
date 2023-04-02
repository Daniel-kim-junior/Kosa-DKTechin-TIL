    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.Arrays;

public class Main {

        public static void main(String[] args) throws IOException {
            // 단어 수학
            // 2개 이상의 단어가 주어졌을때 각 알파벳 대문자를 0~9 숫자로 변경해서
            // 더한 값이 가장 큰 수를 구하라
            // ex ) AAA , AAA -> 999 + 999 = 1998

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine());

            // 중요한건 단어들의 각자의 위치
            // 각 위치에 따라 숫자의 기대값이 달라짐

            // 단어의 개수가 10개이고 최대 길이는 8이기 때문에  10^9을 넘지 않음 따라서 int로 선언
            // 각 알파벳의 가중치를 저장할 배열
            int record[] = new int[26];

            for(int i = 0; i < N; i++) {
                String s = br.readLine();
                int l = (int) Math.pow(10, s.length() - 1);
                for(int j = 0; j < s.length(); j++) {
                    char c = s.charAt(j);
                    record[c - 65] += l;
                    l /= 10;
                }
            }

            Arrays.sort(record);
            int result = 0;
            int p = 9;
            for(int i = record.length - 1; i >= 0; i--) {
                if(record[i] == 0) {
                    break;
                }
                result += record[i] * p;
                p--;
            }
            System.out.println(result);

        }
    }

가장 가중치가 높은 알파벳에 가장 높은 숫자를 정해주고
그 값을 더해주면 되는 문제

