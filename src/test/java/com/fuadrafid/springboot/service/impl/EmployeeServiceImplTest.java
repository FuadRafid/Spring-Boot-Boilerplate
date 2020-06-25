package com.fuadrafid.springboot.service.impl;

import com.fuadrafid.springboot.dto.request.employee.CreateEmployeeDto;
import com.fuadrafid.springboot.dto.response.employee.createemployee.CreateEmployeeResponseDto;
import com.fuadrafid.springboot.dto.response.employee.getemployee.GetEmployeeResponseDto;
import com.fuadrafid.springboot.exception.ApplicationInternalException;
import com.fuadrafid.springboot.util.RestRequestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
    @InjectMocks
    private EmployeeServiceImpl service;

    @Mock
    private RestRequestUtil restRequestUtil;

    @Test
    void test_getEmployee_givenHttpStatusOk_shouldReturnCorrectResponse() {
        ClientResponse clientResponse = mock(ClientResponse.class, RETURNS_DEEP_STUBS);
        GetEmployeeResponseDto employeeData = new GetEmployeeResponseDto();
        employeeData.setStatus("success");

        when(clientResponse.bodyToMono(GetEmployeeResponseDto.class).block()).thenReturn(employeeData);
        when(clientResponse.statusCode()).thenReturn(HttpStatus.OK);

        when(restRequestUtil.getSync(any())).thenReturn(clientResponse);
        assertThat(service.getEmployees()).isEqualTo(employeeData);

    }

    @Test
    void test_getEmployee_givenHttpStatusNotOk_shouldReturnErrorResponse() {
        ClientResponse clientResponse = mock(ClientResponse.class, RETURNS_DEEP_STUBS);
        GetEmployeeResponseDto employeeData = new GetEmployeeResponseDto();
        employeeData.setStatus("success");

        when(clientResponse.statusCode()).thenReturn(HttpStatus.INTERNAL_SERVER_ERROR);

        when(restRequestUtil.getSync(any())).thenReturn(clientResponse);

        ApplicationInternalException exception = assertThrows(ApplicationInternalException.class, () -> service.getEmployees());

        assertThat(exception.getMessage()).isEqualTo("Failed to get employee data, error response from employee server");

    }

    @Test
    void test_createEmployee_givenHttpStatusNotOk_shouldReturnCorrectResponse() {
        ClientResponse clientResponse = mock(ClientResponse.class, RETURNS_DEEP_STUBS);

        final CreateEmployeeDto mockRequest = new CreateEmployeeDto();
        mockRequest.setName("abc");
        mockRequest.setAge(12);
        mockRequest.setSalary(111111);

        final CreateEmployeeResponseDto mockResponse = new CreateEmployeeResponseDto();
        mockResponse.setStatus("some status");

        when(clientResponse.bodyToMono(CreateEmployeeResponseDto.class).block()).thenReturn(mockResponse);
        when(clientResponse.statusCode()).thenReturn(HttpStatus.OK);

        when(restRequestUtil.postSync(any(), any(), any())).thenReturn(clientResponse);

        final var result = service.createEmployee(mockRequest);
        assertThat(result).isEqualTo(mockResponse);
    }

    @Test
    void test_createEmployee_givenHTTPStatusNotOk_shouldReturnErrorResponse() {
        ClientResponse clientResponse = mock(ClientResponse.class, RETURNS_DEEP_STUBS);

        final CreateEmployeeDto mockRequest = new CreateEmployeeDto();
        mockRequest.setName("abc");
        mockRequest.setAge(12);
        mockRequest.setSalary(111111);

        final CreateEmployeeResponseDto mockResponse = new CreateEmployeeResponseDto();
        mockResponse.setStatus("some status");

        when(clientResponse.statusCode()).thenReturn(HttpStatus.INTERNAL_SERVER_ERROR);

        when(restRequestUtil.postSync(any(), any(), any())).thenReturn(clientResponse);

        ApplicationInternalException exception = assertThrows(ApplicationInternalException.class, () -> service.createEmployee(mockRequest));
        assertThat(exception.getMessage()).isEqualTo("Failed to create employee, error response from employee server");

    }
}