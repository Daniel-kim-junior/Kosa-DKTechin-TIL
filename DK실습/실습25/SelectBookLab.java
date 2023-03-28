package JDBCExam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.HashMap;

public class SelectBookLab {

    static HashMap<String, String> bookList;
    public static void main(String[] args) throws SQLException {
            String url = "jdbc:mysql://localhost:3306/edudb?characterEncoding=UTF-8&serverTimezone=UTC";
            String user = "jdbctest";
            String passwd = "jdbctest";
        //============================================
        // Map
        bookList = new HashMap<>();
        bookList.put("b01", "프로그래밍 언어");
        bookList.put("b02", "웹 프로그래밍");
        bookList.put("b03", "빅데이터");
        bookList.put("b04", "데이터베이스");
        bookList.put("b05", "인프라");

        //============================================
        // 커넥션
        Connection conn = DriverManager.getConnection(url, user, passwd);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int select;
        String sql;
        ResultSet resultSet;
        //============================================

        while (true) {
            // 프롬프트
            System.out.println(prompt());

            //============================================
            try {
                // 셀렉트
                select = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
                continue;
            } catch (IOException e) {
                System.out.println("잘못 된 입력입니다.");
                continue;
            }
            //============================================
            // sql문 만드는 메소드
            sql = makeSQL(select);

            //============================================
            // 종료
            if(sql.equals("")) {
                conn.close();
                return;
            }
            //============================================
            // make sql resultset
            resultSet = sqlSearch(conn.prepareStatement(sql) , select);
            // print Result
            printResult(resultSet, select);

            //============================================

        }
    }

    private static ResultSet sqlSearch(PreparedStatement pstmt, int select) throws SQLException {
        // sql 파라메터 세팅
        if(select == 4) {
            pstmt.setString(1,"B02");
        } else if(select == 5) {
            pstmt.setString(1,"B04");
            pstmt.setString(2,"B05");

        } else if(select == 6) {
            pstmt.setString(1,"%자바%");
        }
        return pstmt.executeQuery();
    }

    public static void printResult(ResultSet rs, int sel) throws SQLException  {
        if(!rs.next()) {
            System.out.println("찾고자 하는 책이 없습니다.");
            return;
        }
        //============================================

        String title, price, kind, id;
        StringBuilder result = new StringBuilder();
        //============================================
//       sql 결과 parsing
        //============================================
        if(sel != 7) System.out.println("ID==책제목=====가격========종류");
        do {
            if(sel == 7) {
                kind = bookList.get(rs.getString(1));
                price = rs.getString(2);
                result.append(kind).append("도서들의 가격 평균은 ").append(price).append("원입니다.\n");
            } else if (sel == 3 || sel == 4 || sel == 5 || sel == 6) {
                title = rs.getString(1);
                price = rs.getString(2);
                result.append("\t" + title + "\t").append(price +"원" + "\n");
            } else {
                id = rs.getString(1);
                title = rs.getString(2);
                price = rs.getString(3);
                kind = bookList.get(rs.getString(4));
                result.append(id + "\t").append(title + "\t").append(price +"원"+"\t").append(kind + "\n");
            }
        } while(rs.next());

        //============================================
        // 결과 출력
        System.out.println(result + "\n\n");
        System.out.println("================================");
        System.out.println();
    }
    public static String makeSQL(int selectNum) {
        //================= 종료 ===========================
        if(selectNum == 0) {
            System.out.println("종료합니다.");
            return "";
        }
        //============================================

//      sql Selection
        switch (selectNum) {
            case 1:
                return "select id, title, format(price, 0), kind from book;";
            case 2:
                return "select id, title, format(price, 0), kind from book order by price desc;";
            case 3:
                return "select title, format(price, 0) from book where price >= 20000;";
            case 4:
                return "select title, format(price, 0) from book where kind = ?;";
            case 5:
                return "select title, format(price, 0) from book where kind in (?, ?);";
            case 6:
                return "select title, format(price, 0) from book where title like ?;";
            case 7:
                return "select kind, format(avg(price), 0) from book group by kind;";
        }

        return "";
    }
    public static String prompt() {
        // 프롬프트
        return "1. 모두 출력하기\n" +
                "2. 가격이 높은 순으로 출력하기\n" +
                "3. 20000 이상의 도서들만 출력하기\n" +
                "4. 웹 프로그래밍 도서들만 출력하기\n" +
                "5. 데이터베이스와 인프라 도서들만 출력하기\n" +
                "6. 도서명에 '자바'를 포함하는 도서들만 출력하기\n" +
                "7. 분류별 도서 가격의 평균을 출력하기\n" +
                "0. 종료하기" +
                "\n" +
                "원하는 메뉴의 번호를 선택 : \n";
    }
}
