package exam;

public class Member {
    private final String name;
    private final String account;
    private final String passwd;
    private final int birthyear;


    public Member(String name, String account, String passwd, int birthyear) {
        this.name = name;
        this.account = account;
        this.passwd = passwd;
        this.birthyear = birthyear;
    }


    @Override
    public String toString() {
        return name + "("+account+","+passwd+","+birthyear+")";
    }
}
