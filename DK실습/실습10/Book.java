package exam;

public class Book {
    private String title;
    private String author;
    private int price;

    Book() {
        this("이것이 자바다", "신용권", 36000);
    };

    Book(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }


    public String getBookInfo() {
        return title + " " + author + " " + price;
    }

}
