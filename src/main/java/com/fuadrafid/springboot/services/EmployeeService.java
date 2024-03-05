package com.fuadrafid.springboot.services;


import com.fuadrafid.springboot.dto.request.employee.CreateEmployeeDto;
import com.fuadrafid.springboot.dto.response.employee.createemployee.CreateEmployeeResponseDto;
import com.fuadrafid.springboot.dto.response.employee.getemployee.GetEmployeeResponseDto;

public interface EmployeeService {
    GetEmployeeResponseDto getEmployees();

    CreateEmployeeResponseDto createEmployee(CreateEmployeeDto requestDto);
}
