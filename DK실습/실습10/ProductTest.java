package exam;

public class ProductTest {
    public static void main(String[] args) {
        Product proList[] = new Product[5];

        for(int i = 0; i < 5; i++) {
            if(i == 0) {
                proList[i] = new Product();
            } else {
                proList[i] = new Product("gg" + i, i * 1000, i * 10000);
            }
            System.out.println(proList[i]);
        }



    }
}
