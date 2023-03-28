package mobile;

public class MobileTest {
    public static void main(String[] args) {
        Mobile lTab = new Ltab("Ltab", 500, "ABC-01");
        Mobile oTab = new Otab("Otab", 1000, "XYZ-20");

        printTitle();
        System.out.println("---------------------------");
        printMobile(lTab);
        printMobile(oTab);

        lTab.charge(10);
        oTab.charge(10);
        System.out.println();
        System.out.println("[10분 충전]");
        printTitle();
        System.out.println("---------------------------");
        printMobile(lTab);
        printMobile(oTab);
        System.out.println();
        System.out.println("[5분 통화]");
        printTitle();
        System.out.println("---------------------------");
        lTab.operate(5);
        oTab.operate(5);
        printMobile(lTab);
        printMobile(oTab);

    }

    public static void printMobile(Mobile mobile) {
        System.out.println(mobile.getMobileName() + "\t" + mobile.getBatterySize() + "\t" + mobile.getOsType());
    }
    public static void printTitle() {
        System.out.println("Mobile" + "\t" +"Battery" + "\t" + "OS");
    }
}
