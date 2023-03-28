package example2;

public class ForLab6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final char c = '&';
		int rand = (int) (Math.random() * 6) + 5;
		
		for(int i = 0; i < rand; i++) {
			for(int j = 0; j <= i; j++) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

}
