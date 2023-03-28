package exam;

public class ControlLab3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int rand;
		String st = "";
		while(true) {
			rand = ControlLab1.makeRandom(120, 1);
			
			if(rand < 50) {
				st += rand + " : 50미만\n";
				if(rand % 10 == 3) {
					st += rand + " : *듀크팀*\n";
				}
			} else if(rand <= 80) {
				st += rand + " : 50이상 80 이하\n";
				if(rand / 10 == 7) {
					st += rand + " : *턱시팀*\n";
				}
				
			} else if(rand <= 100) {
				continue;
			} else {
				break;
			}
			
		}
		System.out.printf("%s", st);
	}

}
