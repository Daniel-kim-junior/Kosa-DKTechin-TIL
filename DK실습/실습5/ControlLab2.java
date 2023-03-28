package exam;

public class ControlLab2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < 11; i++) {
			if(i % 3 == 0 || i % 4 == 0) {
				continue;
			}
			sb.append(i).append("\n");
		}
		System.out.println(sb);
	}

}
