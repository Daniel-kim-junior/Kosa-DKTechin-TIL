package day23;

import day6.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AnonyInnerLab {
    public static void main(String[] args) {
        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(new Book("자바의 정석", "남궁성", 27000));
        bookList.add(new Book("챗 GPT", "반병현", 11700));
        bookList.add(new Book("스타트 스프링 부트", "남가람", 27000));
        bookList.add(new Book("Doit! 자바 프로그래밍", "박은중", 22500));
        bookList.add(new Book("이것이 자바다", "신용권,임경균", 36000));
        bookList.stream().forEach(i -> System.out.println(i.getBookInfo()));
        Collections.sort(bookList, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getPrice() - o2.getPrice();
            }
        });

//        Collections.sort(bookList, (o1, o2) -> o1.getPrice() - o2.getPrice());

        System.out.println("-------------소트 후-----------------");
        bookList.stream().forEach(i -> System.out.println(i.getBookInfo()));


    }
}
