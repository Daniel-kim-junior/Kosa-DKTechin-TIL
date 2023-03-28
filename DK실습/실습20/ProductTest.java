package Collection;

import java.util.HashSet;

public class ProductTest {
    public static void main(String[] args) {
        Product tv = new Product("p100", "TV", "20000");
        Product computer = new Product("p200", "Computer", "10000");
        Product mp3 = new Product("p200", "MP3", "700");
        Product audio = new Product("p300", "Audio", "1000");
        Product[] arr = {tv, computer, mp3, audio};
        HashSet<Product> hs = new HashSet<>();
        for(int i = 0; i < arr.length; i++) {
            if(hs.contains(arr[i])) {
                System.out.println("동일한 ID의 제품이 이미 저장되어 있습니다.");
            } else {
                hs.add(arr[i]);
                System.out.println("성공적으로 저장되었습니다.");
            }
        }

        System.out.println("제품 ID" + "\t" + "제품명" + "가격");
        System.out.println("----------------------------------");
        for(Product p: hs) {
            System.out.println(p);
        }

    }
}
