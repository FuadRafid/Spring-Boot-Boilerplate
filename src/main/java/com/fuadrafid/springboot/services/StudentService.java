package com.fuadrafid.springboot.services;

import com.fuadrafid.springboot.dto.db.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(String id);
    void deleteStudent(String id);
    Student createStudent(Student student);
    Student updateStudent(Student student);
}
