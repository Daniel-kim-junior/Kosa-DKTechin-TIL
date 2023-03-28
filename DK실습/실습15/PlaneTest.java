package 실습6;

public class PlaneTest {
    public static void main(String[] args) {
        Plane planeList[] = new Plane[2];
        planeList[0] = new Airplane("L747", 1000);
        planeList[1] = new CargoPlane("C40", 1000);

        printInfo(planeList);
        for(int i = 0; i < 2; i++) {
            planeList[i].flight(100);
        }
        printInfo(planeList);

        for(int i = 0; i < 2; i++) {
            planeList[i].refuel(200);
        }
        printInfo(planeList);


    }

    public static void printInfo(Plane[] list) {
        System.out.println("Plane\tfuelSize");
        System.out.println("----------------");
        int len = list.length;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len; i++) {
            sb.append(list[i].getPlaneName() + "\t\t" + list[i].getFuelSize() + "\n");
        }
        System.out.println(sb);
    }
}
