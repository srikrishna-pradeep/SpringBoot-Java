package SpringPostgresDatabase.Dao;

import SpringPostgresDatabase.model.Student;

import java.util.List;

public interface StudentDao {
    List<Student> findAll();


    void insertStudent(Student student);

    void updateStudent(Student student);

    void executeUpdateStudent(Student student);

    void deleteStudent(Student student);
}
