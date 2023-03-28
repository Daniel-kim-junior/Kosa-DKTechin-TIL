package day11;

import java.util.LinkedList;

class Person {
	private String name;
	
	Person(String name) {
		this.name = name;
	}
	public String getInfo() {
		return name;
	}
}

class Friend extends Person {
	private String phoneNum;
	private String email;
	
	Friend(String name, String phoneNum, String email) {
		super(name);
		this.phoneNum = phoneNum;
		this.email = email;
	}
	public String getInfo() {
		return super.getInfo() + "   " + phoneNum + "   " + email;
	}
}

public class LinkedListLab1 {

	public static void main(String[] args) {
		LinkedList<Friend> friend = new LinkedList<>();
		friend.add(new Friend("뚜비", "01012345678", "뚜비@naver.com"));
		friend.add(new Friend("나나", "01011112222", "나나@naver.com"));
		friend.add(new Friend("뽀오", "01033334444", "뽀오@naver.com"));
		friend.add(new Friend("텔레", "01055556666", "텔레@naver.com"));
		friend.add(new Friend("토비", "01077778888", "토비@naver.com"));

		System.out.println("이름   전화번호         메일주소");
		System.out.println("---------------------------------------");
		for(int i = 0; i < 5; i++) {
			System.out.printf("%s\n", friend.get(i).getInfo());
		}
	}

}
