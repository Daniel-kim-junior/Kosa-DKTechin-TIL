package JDBCExam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertBookLab {
    public static void main(String[] args) throws SQLException, IOException {
        String url = "jdbc:mysql://localhost:3306/edudb?characterEncoding=UTF-8&serverTimezone=UTC";
        String user = "jdbctest";
        String passwd = "jdbctest";
        Connection conn = DriverManager.getConnection(url, user, passwd);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String bookKind, bookTitle, sql, flag;
        int bookPrice, count = 0;


        while(true) {
            try {
                System.out.println("도서명을 입력해주세요.");
                bookTitle = br.readLine();
                System.out.println("가격을 입력해주세요");
                bookPrice = Integer.parseInt(br.readLine());
                System.out.println("도서분류에 대한 넘버를 입력 하세요");
                System.out.println("1.프로그래밍 언어\n2.웹 프로그래밍\n3.빅데이터\n4.데이터베이스\n5.인프라");
                System.out.print("1~5");
                bookKind = "b0" + br.readLine();

            } catch (NumberFormatException n) {
                System.out.println("숫자가 아닙니다 다시 입력해주세요");
                continue;
            }
            sql = "insert into book (title, price, kind) values (?, ?, ?)";
            try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, bookTitle);
                pstmt.setInt(2, bookPrice);
                pstmt.setString(3, bookKind);
                pstmt.executeUpdate();
                System.out.println("정보가 입력되었습니다.");
                count++;
                System.out.println("계속 입력하겠습니까?(y / n)");
                flag = br.readLine().toLowerCase();
                if(flag.equals("n")) {
                    System.out.println(count +"개의 데이터 입력 완료!");
                    return;
                }
                if(!flag.equals("y")) {
                    throw new IllegalArgumentException();
                }
            } catch (NumberFormatException n) {
                System.out.println("숫자가 아닙니다. 다시 입력해주세요");
            } catch (IllegalArgumentException e) {
                System.out.println("입력이 잘못 되었습니다. 다시 입력해주세요");
            }
        }

    }
}
