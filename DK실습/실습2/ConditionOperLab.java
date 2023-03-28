package day2;

public class ConditionOperLab {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int rand = (int)(Math.random() * 5 + 1);
		int rst;
		if(rand == 1) {
			rst = 300 + 50;
		} else if(rand == 2) {
			rst = 300 - 50;
		} else if(rand == 3) {
			rst = 300 * 50;
		} else if(rand == 4) {
			rst = 300 / 50;
		} else {
			rst = 300 % 50;
		}
		System.out.printf("결과값 : %d", rst);
	}

}
