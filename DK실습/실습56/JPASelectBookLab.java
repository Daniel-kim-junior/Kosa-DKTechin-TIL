package jpamvcexam.mainview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import jpamvcexam.model.vo.Book;

public class JPASelectBookLab {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("emptest");
    private static EntityManager entityManager = emf.createEntityManager();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            System.out.println("1. 모두 출력하기");
            System.out.println("2. 가격이 높은 순으로 출력하기");
            System.out.println("3. 20000원 이상의 도서들만 출력하기");
            System.out.println("4. id 가 3번인 도서 출력하기");
            System.out.println("5. 도서명에 '자바' 또는 '스프링' 이 들어간 도서 출력하기");
            System.out.println("6. 분류별 도서 가격의 합을 출력하기");
            System.out.println("7. 종료");
            int n = Integer.parseInt(br.readLine());
            if(n == 7) break;
            switch (n) {
                case 1:
                    selectAll();
                    break;
                case 2:
                    selectPriceDesc();
                    break;
                case 3:
                    selectPriceOver20000();
                    break;
                case 4:
                    selectId3();
                    break;
                case 5:
                    selectJavaOrSpring(new Random().nextBoolean());
                    break;
                case 6:
                    selectSumPriceByCategory();
                    break;
            }
        }


        entityManager.close();
        emf.close();
    }

    private static void selectAll() {
        entityManager.createQuery("select t from book t", Book.class)
                .getResultList()
                .forEach(System.out::println);
    }

    private static void selectPriceDesc() {
        entityManager.createQuery("select t from book t order by t.price desc", Book.class)
                .getResultList()
                .forEach(System.out::println);
    }

    private static void selectPriceOver20000() {
        entityManager.createQuery("select t from book t where t.price >= 20000", Book.class)
                .getResultList()
                .forEach(System.out::println);
    }

    private static void selectId3() {
        System.out.println(entityManager.createQuery("select t from book t where t.id = 3", Book.class)
                .getSingleResult());
    }

    private static void selectJavaOrSpring(boolean flag) {
        String bookParam = flag ? "자바" : "스프링";
        TypedQuery<Book> query = entityManager.createQuery(
                "select t from book t where t.bookName like :bookParam", Book.class);
        query.setParameter("bookParam", "%" + bookParam + "%");
        query.getResultList().forEach(System.out::println);
    }

    private static void selectSumPriceByCategory() {
        Query query = entityManager.createQuery("select t.bookType, sum(t.price) from book t group by t.bookType");
        List<Object[]> resultList = query.getResultList();
        resultList.stream().forEach(objects -> System.out.println("분류 코드" + objects[0] + " : " + objects[1]));


    }

}
