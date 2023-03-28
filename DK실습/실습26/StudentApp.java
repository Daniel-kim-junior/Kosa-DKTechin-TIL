package mvclab.view;

import mvclab.MySQLConnect;
import mvclab.controller.StudentController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class StudentApp {
    private static final StudentController studentController = new StudentController();
    public static void main(String[] args) {
        boolean flag = false;
        int n;
        int score;
        String name;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(!flag) {
            try {
                System.out.println("처리하려는 기능을 선택하세요.\n"
                        + "1.학생 정보 출력\n"
                        + "2.학생 정보 입력\n"
                        + "3.학생 정보 삭제\n"
                        + "4.학생 정보 수정\n"
                        + "5.학생 점수 확인\n"
                        + "6.종료"
                        + "입력 :");

                n = Integer.parseInt(br.readLine());
                if(!checkInput(n)) {
                    throw new NumberFormatException();
                }
                switch (n) {
                    case 1:
                        studentController.printAll();
                        break;
                    case 2:
                        System.out.println("입력하려는 학생의 이름을 입력하세요");
                        name = br.readLine();
                        System.out.println("입력하려는 학생의 점수를 입력하세요");
                        score = Integer.parseInt(br.readLine());
                        studentController.insert(name, score);
                        break;
                    case 3:
                        System.out.println("삭제하려는 학생의 이름을 입력하세요");
                        name = br.readLine();
                        studentController.delete(name);
                        break;

                    case 4:
                        System.out.println("변경하려는 학생의 이름을 입력하세요");
                        name = br.readLine();
                        System.out.println("변경하려는 학생의 점수를 입력하세요");
                        score = Integer.parseInt(br.readLine());
                        studentController.update(name, score);

                        break;

                    case 5:
                        System.out.println("점수를 확인하고 싶은 학생의 이름을 입력하세요");
                        name = br.readLine();
                        studentController.printScore(name);
                        break;

                    case 6:
                        flag = true;
                        System.out.println("프로그램을 종료합니다.");
                }

            } catch (SQLException e) {
                System.out.println("SQL 에러입니다.");
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해야합니다.");
            } catch (IOException e) {
                System.out.println("잘못 된 입력입니다.");
            } finally {
                MySQLConnect.close(MySQLConnect.connect());
            }
        }


    }
    private static boolean checkInput(int input) {
        return input >= 1 && input < 7 ? true : false;
    }
}
