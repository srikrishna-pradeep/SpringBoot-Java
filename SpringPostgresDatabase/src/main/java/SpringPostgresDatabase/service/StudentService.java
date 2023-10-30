package SpringPostgresDatabase.service;


import SpringPostgresDatabase.Dao.StudentDaoImplementation;
import SpringPostgresDatabase.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentDaoImplementation studentDaoImplementation;

    public List<Student> getStudents() {
        return studentDaoImplementation.findAll();
    }

    public void addStudent(Student student) {
        studentDaoImplementation.insertStudent(student);
    }

    public void updateStudent(Student student) {
        studentDaoImplementation.updateStudent(student);
    }

    public void deleteStudent(Student student) {
        studentDaoImplementation.deleteStudent(student);
    }
}
