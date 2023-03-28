package exam;

public class ControlLab1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int rand;
		int cnt = 0;
		while(true) {
			rand = makeRandom(11, 10);
		
			
			if(rand % 3 == 0 || rand % 5 == 0) {
				cnt++;
				System.out.println(rand * (rand + 1) / 2);
				continue;
			}

			if(rand == 11 || rand == 17 || rand == 19) {
				System.out.println(cnt + "회 반복함");
				return;
			}
			
			System.out.println("재수행");
		}
		
		
	}

	public static int makeRandom(int n, int s) {
		return (int)(Math.random() * n) + s;
	}
}
