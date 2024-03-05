package com.fuadrafid.springboot.services.impl;

import com.fuadrafid.springboot.dto.db.Student;
import com.fuadrafid.springboot.exception.ApplicationInternalException;
import com.fuadrafid.springboot.repositories.StudentRepository;
import com.fuadrafid.springboot.services.StudentService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        try {
            return studentRepository.findAll();
        } catch (DataAccessException e) {
            throw new ApplicationInternalException("Failed to get students", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Student getStudentById(String id) {
        try {
            Student student = studentRepository.findById(id).orElse(null);
            if (student == null)
                throw new ApplicationInternalException(String.format("Student with id: %s does not exist", id), HttpStatus.INTERNAL_SERVER_ERROR);
            return student;
        } catch (DataAccessException e) {
            throw new ApplicationInternalException(String.format("Failed to get student with id: %s", id), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteStudent(String id) {
        try {
            studentRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new ApplicationInternalException(String.format("Failed to delete student with id: %s", id), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Student createStudent(Student student) {
        try {
            return studentRepository.save(student);
        } catch (DataAccessException e) {
            throw new ApplicationInternalException(String.format("Failed to create student with id: %s", student.getId()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Student updateStudent(Student student) {
        try {
            Student dbStudent = studentRepository.findById(student.getId()).orElse(null);
            if (dbStudent == null)
                throw new ApplicationInternalException(String.format("Student with id: %s does not exist", student.getId()), HttpStatus.INTERNAL_SERVER_ERROR);
            dbStudent.setStudentAge(student.getStudentAge());
            dbStudent.setStudentName(student.getStudentName());
            dbStudent.setStudentClass(student.getStudentClass());
            dbStudent.setProfileImage(student.getProfileImage());

            return studentRepository.save(dbStudent);
        } catch (DataAccessException e) {
            throw new ApplicationInternalException(String.format("Failed to update student with id: %s", student.getId()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
