package exam;

public class WhileLab2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int pairNum1;
		int pairNum2;
		while(true) {
			pairNum1 = ControlLab1.makeRandom(6, 1);
			pairNum2 = ControlLab1.makeRandom(6, 1);
			
			if(pairNum1 == pairNum2) {
				System.out.println("게임 끝");
				return;
			}
			
			printMessage(pairNum1, pairNum2);
			
			
		}
	}
	
	public static void printMessage(int pairNum1, int pairNum2) {
		if(pairNum1 < pairNum2) {
			System.out.println("pairNum1이 pairNum2보다 작다.");
		} else {
			System.out.println("pairNum1이 pairNum2보다 크다.");
		}
	}
	

}
