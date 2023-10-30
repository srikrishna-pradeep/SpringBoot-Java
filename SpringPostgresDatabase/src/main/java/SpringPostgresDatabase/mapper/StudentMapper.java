package SpringPostgresDatabase.mapper;

import SpringPostgresDatabase.model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student();
        student.setId(Integer.parseInt(resultSet.getString("id")));
        student.setDepartment(resultSet.getString("department"));
        student.setFirstName(resultSet.getString("firstName"));
        student.setLastName(resultSet.getString("lastName"));
        return student;
    }
}
