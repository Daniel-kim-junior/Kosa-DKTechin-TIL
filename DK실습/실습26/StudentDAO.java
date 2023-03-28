package mvclab.model;

import mvclab.MySQLConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private final Connection connection = MySQLConnect.connect();
    private PreparedStatement pstmt;
    private ResultSet resultSet;
    private List<StudentDTO> result;
    public boolean insertStudent(StudentDTO dto) throws SQLException {

        pstmt = connection.prepareStatement("insert student (name, score) values (?,?)");
        pstmt.setString(1, dto.getName());
        pstmt.setInt(2, dto.getScore());
        return pstmt.executeUpdate() == 1 ? true : false;
    }

    public List<StudentDTO> getAllStudent() throws SQLException {

         pstmt = connection.prepareStatement("select name, score from student");
        resultSet = pstmt.executeQuery();
        result = new ArrayList<>();
         StudentDTO dto;
        while(resultSet.next()) {
            dto = new StudentDTO(resultSet.getString(1), resultSet.getInt(2));
            result.add(dto);
        }

        return result;
    }

    public int getScore(StudentDTO dto) throws SQLException {
        pstmt = connection.prepareStatement("select score from student where name = ?");
        pstmt.setString(1, dto.getName());
        ResultSet resultSet = pstmt.executeQuery();

        return resultSet.next() ? resultSet.getInt(1) : -1;
    }

    public boolean updateStudent(StudentDTO dto) throws SQLException {
        pstmt = connection.prepareStatement("update student set score = ? where name = ?");
        pstmt.setInt(1, dto.getScore());
        pstmt.setString(2, dto.getName());

        return pstmt.executeUpdate() == 1 ? true : false;
    }

    public boolean deleteStudent(StudentDTO dto) throws SQLException {
        pstmt = connection.prepareStatement("delete from student where name = ?");
        pstmt.setString(1, dto.getName());

        return pstmt.executeUpdate() == 1 ? true : false;
    }
}
