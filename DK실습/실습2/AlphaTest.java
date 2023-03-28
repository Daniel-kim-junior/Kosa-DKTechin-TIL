package day2;

public class AlphaTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = (int) (Math.random() * 26 + 1);

		char c = (char) (num + 64);
		System.out.printf("추출된 숫자 : %d\n", num);
		System.out.println(c);
	}

}
