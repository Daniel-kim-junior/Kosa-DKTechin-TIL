package exam;

public class BookTest {
    public static void main(String[] args) {
        Book book1 = new Book();
        Book book2 = new Book("꽃으로도 때리지마라", "김민성", 13000);
        Book book3 = new Book("꽃으로도 때리지마라2", "김민성", 13000);
        Book book4 = new Book("꽃으로도 때리지마라3", "김민성", 13000);
        Book book5 = new Book("꽃으로도 때리지마라4", "김민성", 13000);


        System.out.println(book1.getBookInfo());
        System.out.println(book2.getBookInfo());
        System.out.println(book3.getBookInfo());
        System.out.println(book4.getBookInfo());
        System.out.println(book5.getBookInfo());

    }
}
