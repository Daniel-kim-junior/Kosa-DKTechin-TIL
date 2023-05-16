package jpamvcexam.model.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="book")
public class Book {
    @Id
    @GeneratedValue
    @Column(name="book_id")
    private String bookId;

    @Column(name = "book_type")
    private String bookType;

    @Column(name = "book_name")
    private String bookName;

    private Integer price;

    @Override
    public String toString() {
        return "Book{" +
               "bookId='" + bookId + '\'' +
               ", bookType='" + bookType + '\'' +
               ", bookName='" + bookName + '\'' +
               ", price=" + price +
               '}';
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    public int getPrice() {
        return price;
    }
}
