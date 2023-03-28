package mvclab.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import mvclab.model.StudentDAO;
import mvclab.model.StudentDTO;

public class StudentController {
    private static final StudentDAO studentDAO = new StudentDAO();

    public void printAll() throws SQLException {
        StringBuilder sb = new StringBuilder();
        List<StudentDTO> allStudent = studentDAO.getAllStudent();
        allStudent.stream().forEach(e -> sb.append(e.getName() + " " + "점수 : " + e.getScore() + "\n"));
        System.out.println(sb);
    }

    public void printScore(String name) throws SQLException {
        StudentDTO studentDTO = new StudentDTO(name, 0);
        int score = studentDAO.getScore(studentDTO);

        String rst = score == -1
                     ? "해당 이름을 가진 학생은 없습니다"
                     : name + "학생의 점수는 "
                        + score + "입니다";
        System.out.println(rst);
    }

    public void insert(String name, int score) throws SQLException {
        System.out.println(printString(studentDAO.insertStudent(new StudentDTO(name, score)), "입력", ""));
    }

    public void update(String name, int score) throws SQLException {
        System.out.println(printString(studentDAO.updateStudent(new StudentDTO(name, score)), "변경", name));
    }

    public void delete(String name) throws SQLException {
        System.out.println(printString(studentDAO.deleteStudent(new StudentDTO(name, 0)), "삭제" ,name));
    }

    public StringBuilder printString(boolean flag, String keyword, String name) {
        StringBuilder sb = new StringBuilder();
        if(keyword.equals("입력")) {
            return flag ? sb.append("입력 성공") : sb.append("입력 실패");
        }

        if(keyword.equals("변경")) {
            return flag ? sb.append(name + "학생의 점수를 변경했습니다.") : sb.append(name + "학생은 존재하지 않습니다.");
        }

        if(keyword.equals("삭제")) {
            return flag ? sb.append(name + "학생의 데이터를 삭제했습니다.") : sb.append(name + "학생은 존재하지 않습니다.");
        }

        return sb;
    }
}
