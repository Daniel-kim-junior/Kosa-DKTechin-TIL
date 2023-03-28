package exam2;

public class Friend extends Person {
    private String phoneNum;
    private String email;

    Friend(String name, String phoneNum, String email) {
        super(name);
        this.phoneNum = phoneNum;
        this.email = email;
    }

    @Override
    public String getInfo() {
        return "이름 : " + super.getInfo() + "핸폰번호 : " + phoneNum + "이메일주소 : " + email;
    }


}
