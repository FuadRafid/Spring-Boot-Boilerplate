package com.fuadrafid.springboot.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CreateExternalApiEmployeeDto {
    @NotBlank(message = "name cannot be blank or null")
    String name;
    @Positive(message = "salary must be positive")
    int salary;
    @Positive(message = "age must be positive")
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
