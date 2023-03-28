package example;

public class ForLab3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int rand1 = (int) (Math.random() * 10 + 1);
		
		int rand2 = (int) (Math.random() * 40 + 1);
		while(rand2 < 30 || rand2 > 40) {
			rand2 = (int) (Math.random() * 40 + 1);
		}
		
		
		int sum = 0;
		for(int i = rand1; i <= rand2; i++) {
			if(i % 2 == 0) {
				sum += i;
			}
		}
		
		System.out.printf("%d 부터 %d 까지의 짝수의 합 : %d", rand1, rand2, sum);
	}

}
