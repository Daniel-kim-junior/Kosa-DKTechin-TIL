package jpamvcexam.mainview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jpamvcexam.controller.StudentController;
import jpamvcexam.model.vo.Student;

public class StudentApp {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StudentController stc = new StudentController();
        String name;
        int score;
        while(true) {
            System.out.println("처리하려는 기능을 선택하세요.");
            System.out.println("1. 학생 정보 출력");
            System.out.println("2. 학생 정보 입력");
            System.out.println("3. 학생 정보 삭제");
            System.out.println("4. 학생 정보 수정");
            System.out.println("5. 학생 정보 확인");
            System.out.println("6. 종료");
            System.out.println("입력 : ");
            String select = br.readLine();
            if(select.equals("1")) {
                stc.printAll();
            } else if(select.equals("2")) {
                System.out.println("삽입하려는 학생 정보를 입력하세요");
                System.out.println("이름");
                name = br.readLine();
                System.out.println("점수");
                score = Integer.parseInt(br.readLine());
                stc.insert(name, score);
            } else if(select.equals("3")) {
                System.out.println("지우려는 학생 이름을 입력하세요");
                System.out.println("이름");
                name = br.readLine();
                stc.delete(name);
            } else if(select.equals("4")) {
                System.out.println("수정하려는 학생 정보를 입력하세요");
                System.out.println("이름");
                name = br.readLine();
                System.out.println("점수");
                score = Integer.parseInt(br.readLine());
                stc.update(name, score);
            } else if(select.equals("5")) {
                System.out.println("확인하려는 학생 이름을 입력하세요");
                name = br.readLine();
                stc.printScore(name);
            } else if(select.equals("6")) {
                System.out.println("프로그램 종료");
                stc.close();
                return;
            } else {
                System.out.println("잘못 입력하셨습니다 1 ~ 6까지 숫자만 입력해주세요");
            }
        }
    }
}
