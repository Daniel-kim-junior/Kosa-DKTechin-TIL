package exercise;

public class StudentTest {
    public static void main(String[] args) {
        Student arr[] = new Student[3];
        for(int i = 0; i < 3; i++) {
            arr[i] = new Student("김민성" + i, i + 20, 170 + i,  i + 50, i + "","철학" + i);

            System.out.println(arr[i].printInformation());
        }

    }
}
