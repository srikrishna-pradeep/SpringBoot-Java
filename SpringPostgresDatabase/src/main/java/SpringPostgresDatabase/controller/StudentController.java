package SpringPostgresDatabase.controller;


import SpringPostgresDatabase.model.Student;
import SpringPostgresDatabase.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/students")
    public List<Student> getStudents(){
        return studentService.getStudents();
    }


    @PostMapping(value = "/students")
    public void addStudent(@RequestBody Student student){
        studentService.addStudent(student);
    }

    @PostMapping(value = "/updateStudent")
    public void updateStudent(@RequestBody Student student){
        studentService.updateStudent(student);
    }


    @PostMapping(value = "/deleteStudent")
    public void deleteStudent(@RequestBody Student student){
        studentService.deleteStudent(student);
    }

}
