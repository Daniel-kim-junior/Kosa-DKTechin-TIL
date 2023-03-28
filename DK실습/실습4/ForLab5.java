package example2;

public class ForLab5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int rand1 = (int) (Math.random() * 8) + 3 ;
		int rand2 = (int) (Math.random() * 3) + 1;
		
		
		if(rand2 == 1) {
			for(int i = 0; i < rand1; i++) {
				System.out.print('*');
			}
		} else if(rand2 == 2) {
			for(int i = 0; i < rand1; i++) {
				System.out.print('$');
			}
		} else {
			for(int i = 0; i < rand1; i++) {
				System.out.print('#');
			}
		}

	}

}
