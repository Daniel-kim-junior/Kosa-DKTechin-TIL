package DBpackage;

import java.sql.*;
import java.util.Random;

public class SelectEmpLab {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/edudb?characterEncoding=UTF-8&serverTimezone=UTC";
        String user = "jdbctest";
        String passwd = "jdbctest";
        Random random = new Random();
        boolean flag = random.nextBoolean();
        String sql = flag ? "Select ename, sal from emp;" : "Select ename, date_format(HIREDATE, '%Y-%m-%d') from emp order by Hiredate;";

        try(
                Connection connection = DriverManager.getConnection(url, user, passwd);
                PreparedStatement pstmt = connection.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                ) {
            if(rs.next()) {
                do {
                    makeStr(flag, rs.getString(1), rs.getString(2));
                } while (rs.next());
            } else {
                System.out.println("검색 결과가 없습니다.");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
    static void makeStr(boolean f, String col1, String col2) {
        if(f) {
            System.out.println(col1 + "직원의 월급은 " + col2 + "달러입니다.");
        } else {
            System.out.println(col1 + "직원은 " + col2 + "에 입사하였습니다.");
        }
    }
}
