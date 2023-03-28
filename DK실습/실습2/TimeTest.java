package day2;

public class TimeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int time = 32150;
		
		int t = time / 3600;
		int mod = time % 3600;
		int m = mod / 60;
		int x = mod % 60;
		StringBuilder sb = new StringBuilder();
		if(t < 10) {
			sb.append("0"+t+"시간 ");
		} else {
			sb.append(t+"시간 ");
		}
		
		if(m < 10) {
			sb.append("0"+m+"분 ");
		} else {
			sb.append(m+"분 ");
		}
		if(x < 10) {
			sb.append("0"+x+"초");
		} else {
			sb.append(x+"초");
		}
		System.out.println(sb);
		
	}
	
}
