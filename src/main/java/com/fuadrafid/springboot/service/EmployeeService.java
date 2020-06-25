package com.fuadrafid.springboot.service;


import com.fuadrafid.springboot.dto.request.CreateExternalApiEmployeeDto;
import com.fuadrafid.springboot.dto.response.externalapi.createemployee.CreateEmployeeResponseDto;
import com.fuadrafid.springboot.dto.response.externalapi.getemployee.GetEmployeeResponseDto;

public interface EmployeeService {
    GetEmployeeResponseDto getEmployees();

    CreateEmployeeResponseDto createEmployee(CreateExternalApiEmployeeDto requestDto);
}
