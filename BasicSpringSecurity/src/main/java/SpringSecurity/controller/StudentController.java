package SpringSecurity.controller;


import SpringSecurity.model.Student;
import SpringSecurity.service.StudentService;
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

    @GetMapping(value = "students/{id}")
    public Student getStudent(@PathVariable int id){
        return studentService.getStudent(id);
    }

    @PostMapping(value = "/students")
    public void addStudent(@RequestBody Student student){
        studentService.addStudent(student);
    }

    @PostMapping(value = "/updateStudent")
    public void updateStudent(@RequestBody Student student){
        studentService.updateStudent(student);
    }


    @PostMapping(value = "/deleteStudent/{id}")
    public void deleteStudent(@PathVariable int id){
        studentService.deleteStudent(id);
    }

}
