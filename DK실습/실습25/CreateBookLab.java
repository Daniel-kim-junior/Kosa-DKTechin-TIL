package JDBCExam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateBookLab {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/edudb?characterEncoding=UTF-8&serverTimezone=UTC";
        String user = "jdbctest";
        String passwd = "jdbctest";
        String sql = "create table book (id int auto_increment primary key, title varchar(90),price int, kind char(3))";
        try(Connection conn = DriverManager.getConnection(url, user, passwd);
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            System.out.println("테이블을 생성하였습니다.");
            pstmt.executeUpdate();
        } catch(SQLException e) {
            System.out.println("오류 발생");
            e.printStackTrace();
        }

    }
}
