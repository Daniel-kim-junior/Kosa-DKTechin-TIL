package example;

public class SwitchLab2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int rand = (int)(Math.random() * 5 + 1);
		int rst;
		switch(rand) {
			
			case 1:
				rst = 300 + 50;
				break;
			case 2:
				rst = 300 - 50;
				break;
			case 3:
				rst = 300 * 50;
				break;
			case 4:
				rst = 300 / 50;
				break;
			case 5:
				rst = 300 % 50;
				break;
			default:
				rst = 0;
		}
		System.out.println("결과값 : " + rst);
	}

}
