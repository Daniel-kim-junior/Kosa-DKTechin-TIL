package DBpackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class CheckEmpLab {
    public static void main(String[] args) throws IOException, SQLException {
        String url = "jdbc:mysql://localhost:3306/edudb?characterEncoding=UTF-8&serverTimezone=UTC";
        String user = "jdbctest";
        String passwd = "jdbctest";
        BufferedReader br;
        String inputName, sql, date, dept, flag;
        Connection conn = DriverManager.getConnection(url, user, passwd);
        while(true) {
            System.out.println("검색하려는 이름을 입력해주세요");
            br = new BufferedReader(new InputStreamReader(System.in));
            inputName = br.readLine();
            sql = "select date_format(HIREDATE, '%Y-%m'), deptno from emp where ename = '" + inputName + "';";
            try(
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    ResultSet rs = pstmt.executeQuery();
            ){
                if(rs.next()) {
                    do {
                        System.out.println(inputName + " 직원은 근무중입니다.");
                        date = rs.getString(1);
                        dept = rs.getString(2);
                        System.out.println(date + "에 입사했고 현재 " + dept + "번 부서에서 근무하고 있습니다.");
                    } while (rs.next());
                } else {
                    System.out.println(inputName + " 직원은 근무하지 않습니다.");
                    System.out.println("계속 검토하시려면 1번 아니면 2번을 눌러주세요.");
                    flag = br.readLine();
                    if(flag.equals("2")) {
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    }
                }

            }
            catch (SQLException e) {
                System.out.println(sql);
                throw new RuntimeException(e);
            }
        }

    }
}
