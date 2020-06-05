package com.fuadrafid.springboot.service.impl;

import com.fuadrafid.springboot.dto.request.CreateExternalApiEmployeeDto;
import com.fuadrafid.springboot.dto.response.externalapi.createemployee.ExternalApiCreateEmployeeResponseDto;
import com.fuadrafid.springboot.dto.response.externalapi.getemployee.ExternalApiGetEmployeeDto;
import com.fuadrafid.springboot.exception.ServiceException;
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
class ExternalRestApiServiceImplTest {
    @InjectMocks
    private ExternalRestApiServiceImpl service;

    @Mock
    private RestRequestUtil restRequestUtil;

    @Test
    void getEmployee_givenHttpStatusOk_shouldReturnCorrectResponse() {
        ClientResponse clientResponse = mock(ClientResponse.class, RETURNS_DEEP_STUBS);
        ExternalApiGetEmployeeDto employeeData = new ExternalApiGetEmployeeDto();
        employeeData.setStatus("success");

        when(clientResponse.bodyToMono(ExternalApiGetEmployeeDto.class).block()).thenReturn(employeeData);
        when(clientResponse.statusCode()).thenReturn(HttpStatus.OK);

        when(restRequestUtil.getSync(any())).thenReturn(clientResponse);
        assertThat(service.getEmployees()).isEqualTo(employeeData);

    }

    @Test
    void getEmployee_givenHttpStatusNotOk_shouldReturnErrorResponse() {
        ClientResponse clientResponse = mock(ClientResponse.class, RETURNS_DEEP_STUBS);
        ExternalApiGetEmployeeDto employeeData = new ExternalApiGetEmployeeDto();
        employeeData.setStatus("success");

        when(clientResponse.statusCode()).thenReturn(HttpStatus.INTERNAL_SERVER_ERROR);

        when(restRequestUtil.getSync(any())).thenReturn(clientResponse);

        ServiceException exception = assertThrows(ServiceException.class, () -> service.getEmployees());

        assertThat(exception.getMessage()).isEqualTo("Failed to get employee data, error response from server");

    }

    @Test
    void createPost_givenHttpStatusNotOk_shouldReturnCorrectResponse() {
        ClientResponse clientResponse = mock(ClientResponse.class, RETURNS_DEEP_STUBS);

        final CreateExternalApiEmployeeDto mockRequest = new CreateExternalApiEmployeeDto();
        mockRequest.setName("abc");
        mockRequest.setAge(12);
        mockRequest.setSalary(111111);

        final ExternalApiCreateEmployeeResponseDto mockResponse = new ExternalApiCreateEmployeeResponseDto();
        mockResponse.setStatus("some status");

        when(clientResponse.bodyToMono(ExternalApiCreateEmployeeResponseDto.class).block()).thenReturn(mockResponse);
        when(clientResponse.statusCode()).thenReturn(HttpStatus.OK);

        when(restRequestUtil.postSync(any(), any(), any())).thenReturn(clientResponse);

        final var result = service.createEmployee(mockRequest);
        assertThat(result).isEqualTo(mockResponse);
    }

    @Test
    void createPost_givenHTTPStatusNotOk_shouldReturnErrorResponse() {
        ClientResponse clientResponse = mock(ClientResponse.class, RETURNS_DEEP_STUBS);

        final CreateExternalApiEmployeeDto mockRequest = new CreateExternalApiEmployeeDto();
        mockRequest.setName("abc");
        mockRequest.setAge(12);
        mockRequest.setSalary(111111);

        final ExternalApiCreateEmployeeResponseDto mockResponse = new ExternalApiCreateEmployeeResponseDto();
        mockResponse.setStatus("some status");

        when(clientResponse.statusCode()).thenReturn(HttpStatus.INTERNAL_SERVER_ERROR);

        when(restRequestUtil.postSync(any(), any(), any())).thenReturn(clientResponse);

        ServiceException exception = assertThrows(ServiceException.class, () -> service.createEmployee(mockRequest));
        assertThat(exception.getMessage()).isEqualTo("Failed to create employee, error response from server");

    }
}