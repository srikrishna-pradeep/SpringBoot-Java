package BasicSpringBoot.service;


import BasicSpringBoot.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentService {

    private List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(173, "Srikrishna Pradeep", "Jorige", "CSE"),
            new Student(166, "Sai Kiran", "Chandolu", "CSE"),
            new Student(177, "Srujan", "Pedapanga", "ECE"))
    );


    public List<Student> getStudents(){
        return students;
    }

    public Student getStudent(int id){
        for(Student student: students){
            if(student.getId() == id)
                return student;
        }
        return null;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public void updateStudent(Student student){
        for(Student s : students){
            if(student.getId() == s.getId()){
                int index = students.indexOf(s);
                students.set(index, student);
            }
        }
    }


    public void deleteStudent(int id){
        for(Student student : students){
            if(id == student.getId()){
                int index = students.indexOf(student);
                students.remove(index);
            }
        }
    }
}
