package com.fuadrafid.springboot.controller;

import com.fuadrafid.springboot.dto.request.employee.CreateEmployeeDto;
import com.fuadrafid.springboot.dto.response.employee.createemployee.CreateEmployeeResponseDto;
import com.fuadrafid.springboot.dto.response.employee.getemployee.GetEmployeeResponseDto;
import com.fuadrafid.springboot.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @InjectMocks
    EmployeeController employeeController;
    @Mock
    EmployeeService service;

    @Test
    void test_getEmployee_shouldReturnCorrectEmployees() {

        GetEmployeeResponseDto employeeData = new GetEmployeeResponseDto();
        employeeData.setStatus("success");

        when(service.getEmployees()).thenReturn(employeeData);
        assertThat(employeeController.getEmployees().getBody()).isEqualTo(employeeData);
    }

    @Test
    void test_createEmployee_shouldReturnCorrectMessage_givenCorrectInput() {
        final CreateEmployeeDto mockRequest = new CreateEmployeeDto();
        mockRequest.setName("abc");
        mockRequest.setAge(12);
        mockRequest.setSalary(111111);

        final CreateEmployeeResponseDto mockResponse = new CreateEmployeeResponseDto();
        mockResponse.setStatus("some status");


        when(service.createEmployee(any())).thenReturn(mockResponse);
        assertThat(employeeController.createEmployee(mockRequest).getBody()).isEqualTo(mockResponse);

    }
}