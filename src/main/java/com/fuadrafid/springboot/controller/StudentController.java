package com.fuadrafid.springboot.controller;

import com.fuadrafid.springboot.dto.db.Student;
import com.fuadrafid.springboot.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable String studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/save")
    public Student saveStudent(Student student) {
        return studentService.createStudent(student);
    }

    @PostMapping("/update")
    public Student updateStudent(Student student) {
        return studentService.updateStudent(student);
    }

}
