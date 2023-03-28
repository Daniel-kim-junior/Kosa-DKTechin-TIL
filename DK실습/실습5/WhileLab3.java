package exam;

public class WhileLab3 {
	public static void main(String[] args) {
		int rand;
		int cnt = 0;
		while(true) {
			rand =  (int)(Math.random() * 31);
			if(rand == 0 || rand >= 27) {
				break;
			}
			cnt++;
			System.out.printf("%d, %c, %d, 0x%x\n", rand, (char)(rand + 64), rand + 64, rand);
			
		}
		
		
		System.out.println("출력횟수는 " + cnt + " 번입니다.");
	}
}
