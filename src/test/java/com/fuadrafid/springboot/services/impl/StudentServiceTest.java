package com.fuadrafid.springboot.services.impl;

import com.fuadrafid.springboot.dto.db.Student;
import com.fuadrafid.springboot.exception.ApplicationInternalException;
import com.fuadrafid.springboot.repositories.StudentRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @InjectMocks
    StudentServiceImpl studentService;

    @Mock
    StudentRepository studentRepository;

    @NotNull
    private List<Student> getData() {
        Student student1 = new Student();
        student1.setId("1");
        student1.setStudentName("John Doe");
        student1.setStudentClass("Grade 6");
        student1.setProfileImage("data/image1");
        student1.setStudentAge(12);

        Student student2 = new Student();
        student2.setId("2");
        student2.setStudentName("Jane Doe");
        student2.setStudentClass("Grade 7");
        student2.setProfileImage("data/image2");
        student2.setStudentAge(13);


        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);

        return students;
    }

    private DataAccessException getException(String message) {
        return new DataAccessException(message) {
            @Override
            public Throwable getRootCause() {
                return super.getRootCause();
            }
        };
    }


    @AfterEach
    public void resetMocks() {
        Mockito.reset(studentRepository);
    }

    @Test
    public void test__getAllStudents__shouldReturnAllStudents__givenStudentsIsPresent() {
        List<Student> students = getData();
        Mockito.when(studentRepository.findAll()).thenReturn(students);
        assertThat(studentService.getAllStudents()).isEqualTo(students);
    }

    @Test
    public void test__getAllStudents__shouldReturnEmpty__givenNoStudentsPresent() {
        Mockito.when(studentRepository.findAll()).thenReturn(new ArrayList<>());
        assertThat(studentService.getAllStudents()).isEmpty();
    }

    @Test
    public void test__getAllStudents__shouldThrowException__givenRepositoryThrowsError() {
        Mockito.when(studentRepository.findAll()).thenThrow(getException("Bad connection"));
        ApplicationInternalException exception = assertThrows(ApplicationInternalException.class, () -> studentService.getAllStudents());
        assertThat(exception.getMessage()).isEqualTo("Failed to get students");
    }


    @Test
    public void test__getStudentByID__shouldReturnCorrectStudent__givenIdIsPresent() {
        List<Student> students = getData();
        Student studentJohn = students.get(0);
        Mockito.when(studentRepository.findById(anyString())).thenReturn(Optional.ofNullable(studentJohn));
        assertThat(studentService.getStudentById("1")).isEqualTo(studentJohn);
    }


    @Test
    public void test__getStudentByID__shouldThrowError__givenIdIsNotPresent() {
        Mockito.when(studentRepository.findById(anyString())).thenReturn(Optional.empty());
        ApplicationInternalException exception = assertThrows(ApplicationInternalException.class, () -> studentService.getStudentById("1"));
        assertThat(exception.getMessage()).isEqualTo("Student with id: 1 does not exist");
    }

    @Test
    public void test__getStudentByID__shouldThrowError__givenRepositoryThrowsError() {
        Mockito.when(studentRepository.findById(anyString())).thenThrow(getException("Bad connection"));
        ApplicationInternalException exception = assertThrows(ApplicationInternalException.class, () -> studentService.getStudentById("1"));
        assertThat(exception.getMessage()).isEqualTo("Failed to get student with id: 1");
    }

    @Test
    public void test__deleteStudent__shouldDeleteStudent__givenNoError() {
        Mockito.doNothing().when(studentRepository).deleteById(anyString());
        studentService.deleteStudent("1");
    }

    @Test
    public void test__deleteStudent__shouldThrowError__givenErrorFromRepository() {
        Mockito.doThrow(getException("Bad Connection")).when(studentRepository).deleteById(anyString());
        ApplicationInternalException exception = assertThrows(ApplicationInternalException.class, () -> studentService.deleteStudent("1"));
        assertThat(exception.getMessage()).isEqualTo("Failed to delete student with id: 1");
    }


    @Test
    public void test__createStudent__shouldCreateStudent__givenNoError() {
        Student student = getData().get(0);
        Mockito.when(studentRepository.save(any(Student.class))).thenReturn(student);
        Student resultStudent = studentService.createStudent(student);
        assertThat(resultStudent.getId()).isEqualTo("1");
        assertThat(resultStudent.getStudentName()).isEqualTo("John Doe");
        assertThat(resultStudent.getStudentClass()).isEqualTo("Grade 6");
        assertThat(resultStudent.getProfileImage()).isEqualTo("data/image1");
        assertThat(resultStudent.getStudentAge()).isEqualTo(12);
    }


    @Test
    public void test__createStudent__shouldThrowError__givenErrorFromRepository() {
        Student student = getData().get(0);
        Mockito.doThrow(getException("Bad Connection")).when(studentRepository).save(any(Student.class));
        ApplicationInternalException exception = assertThrows(ApplicationInternalException.class, () -> studentService.createStudent(student));
        assertThat(exception.getMessage()).isEqualTo("Failed to create student with id: 1");
    }


    @Test
    public void test__updateStudent__shouldUpdateStudent__givenNoError() {
        Student student = getData().get(0);
        student.setStudentAge(14);
        Mockito.when(studentRepository.findById("1")).thenReturn(Optional.of(student));
        Mockito.when(studentRepository.save(any(Student.class))).thenReturn(student);
        Student resultStudent = studentService.updateStudent(student);
        assertThat(resultStudent.getId()).isEqualTo("1");
        assertThat(resultStudent.getStudentName()).isEqualTo("John Doe");
        assertThat(resultStudent.getStudentClass()).isEqualTo("Grade 6");
        assertThat(resultStudent.getProfileImage()).isEqualTo("data/image1");
        assertThat(resultStudent.getStudentAge()).isEqualTo(14);
    }


    @Test
    public void test__updateStudent__shouldThrowError__givenStudentNotPresent() {
        Student student = getData().get(0);
        student.setStudentAge(14);
        Mockito.when(studentRepository.findById(anyString())).thenReturn(Optional.empty());
        ApplicationInternalException exception = assertThrows(ApplicationInternalException.class, () -> studentService.updateStudent(student));
        assertThat(exception.getMessage()).isEqualTo("Student with id: 1 does not exist");
    }


    @Test
    public void test__updateStudent__shouldThrowError__givenErrorFromRepository() {
        Student student = getData().get(0);
        student.setStudentAge(14);
        Mockito.when(studentRepository.findById(anyString())).thenThrow(getException("Bad Connection"));
        ApplicationInternalException exception = assertThrows(ApplicationInternalException.class, () -> studentService.updateStudent(student));
        assertThat(exception.getMessage()).isEqualTo("Failed to update student with id: 1");


        Mockito.doThrow(getException("Bad Connection")).when(studentRepository).save(any(Student.class));
        exception = assertThrows(ApplicationInternalException.class, () -> studentService.updateStudent(student));
        assertThat(exception.getMessage()).isEqualTo("Failed to update student with id: 1");


    }


}
