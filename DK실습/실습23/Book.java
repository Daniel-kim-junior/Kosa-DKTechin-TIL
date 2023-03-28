package day6;

public class Book {
    private String title;
    private String author;
    private int price;
    public int getPrice() {
        return price;
    }

    public Book(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }



    public String getBookInfo() {
        return title + " " + author + " " + price;
    }

}
