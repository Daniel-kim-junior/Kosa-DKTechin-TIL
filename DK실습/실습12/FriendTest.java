package exam2;

public class FriendTest {
    public static void main(String[] args) {
        Friend friendList[] = new Friend[5];
        for(int i = 0; i < 5; i++) {
            friendList[i] = new Friend("김민성" + 1 + " ", " " + (i + 1) + " ", i + " " + "gmail@com");
        }
        for(int i = 0; i < 5; i++) {
            System.out.println(friendList[i].getInfo());
        }
    }
}
