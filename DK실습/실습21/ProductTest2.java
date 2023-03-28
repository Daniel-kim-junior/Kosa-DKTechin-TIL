package Collection;

import java.util.Collections;
import java.util.LinkedList;

public class ProductTest2 {
    public static void main(String[] args) {
        Product2 tv = new Product2("p100", "TV", "20000");
        Product2 computer = new Product2("p200", "Computer", "10000");
        Product2 mp3 = new Product2("p200", "MP3", "700");
        Product2 audio = new Product2("p300", "Audio", "1000");
        LinkedList<Product2> linkedList = new LinkedList<>();
        linkedList.add(tv);
        linkedList.add(computer);
        linkedList.add(mp3);
        linkedList.add(audio);
        Collections.sort(linkedList);

        System.out.println("제품ID" + "\t\t" + "제품명" + "\t\t"+ "가격");
        System.out.println("----------------------------------");
        for(Product2 p: linkedList) {
            System.out.println(p);
        }

    }
}
