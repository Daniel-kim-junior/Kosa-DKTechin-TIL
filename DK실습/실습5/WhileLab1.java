package exam;

public class WhileLab1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int rand = (int) (Math.random() * 6) + 5;
		System.out.println("[ for 결과 ]");
		for(int i = 1; i <= rand; i++) {
			System.out.printf("%d -> %d\n", i, i*i);
		}
		System.out.println("[ while 결과 ]");
		int cnt = 0;
		while(cnt++ <= rand) {
			System.out.printf("%d -> %d\n", cnt, cnt*cnt);
			
		}
		
	}

}
