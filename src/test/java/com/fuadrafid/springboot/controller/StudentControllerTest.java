package com.fuadrafid.springboot.controller;

import com.fuadrafid.springboot.dto.db.Student;
import com.fuadrafid.springboot.services.StudentService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {
    @InjectMocks
    StudentController studentController;
    @Mock
    StudentService studentService;

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

    @Test
    void test__getStudentById__shouldReturnCorrectValue__givenNoError() {
        Student student = getData().get(0);
        Mockito.when(studentService.getStudentById(anyString())).thenReturn(student);
        assertThat(studentController.getStudentById("1")).isEqualTo(student);
        Mockito.verify(studentService,Mockito.times(1)).getStudentById("1");
    }

    @Test
    void test__getAllStudents__shouldReturnCorrectValue__givenNoError() {
        List<Student> students = getData();
        Mockito.when(studentService.getAllStudents()).thenReturn(students);
        assertThat(studentController.getAllStudents()).isEqualTo(students);
        Mockito.verify(studentService,Mockito.times(1)).getAllStudents();
    }

    @Test
    void test__saveStudent__shouldReturnCorrectValue__givenNoError() {
        Student student = getData().get(0);
        Mockito.when(studentService.createStudent(any(Student.class))).thenReturn(student);
        assertThat(studentController.saveStudent(student)).isEqualTo(student);
        Mockito.verify(studentService,Mockito.times(1)).createStudent(student);
    }

    @Test
    void test__updateStudent__shouldReturnCorrectValue__givenNoError() {
        Student student = getData().get(0);
        Mockito.when(studentService.updateStudent(any(Student.class))).thenReturn(student);
        assertThat(studentController.updateStudent(student)).isEqualTo(student);
        Mockito.verify(studentService,Mockito.times(1)).updateStudent(student);
    }

}
