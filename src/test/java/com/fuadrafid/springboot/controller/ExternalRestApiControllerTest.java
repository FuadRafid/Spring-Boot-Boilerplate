package com.fuadrafid.springboot.controller;

import com.fuadrafid.springboot.dto.request.CreateExternalApiEmployeeDto;
import com.fuadrafid.springboot.dto.response.externalapi.createemployee.ExternalApiCreateEmployeeResponseDto;
import com.fuadrafid.springboot.dto.response.externalapi.getemployee.ExternalApiGetEmployeeReponseDto;
import com.fuadrafid.springboot.service.ExternalRestApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExternalRestApiControllerTest {

    @InjectMocks
    ExternalRestApiController externalRestApiController;

    @Mock
    ExternalRestApiService service;

    @Test
    void helloMessage_shouldReturnCorrectMessage() {
        assertThat(Objects.requireNonNull(externalRestApiController.helloMessage().getBody()).getMessage()).isEqualTo("This url calls an external Api.");
    }

    @Test
    void getPosts_shouldReturnCorrectPosts() {

        ExternalApiGetEmployeeReponseDto employeeData = new ExternalApiGetEmployeeReponseDto();
        employeeData.setStatus("success");

        when(service.getEmployees()).thenReturn(employeeData);
        assertThat(externalRestApiController.getPosts().getBody()).isEqualTo(employeeData);
    }

    @Test
    void createPost_shouldReturnCorrectMessage_givenCorrectInput() {
        final CreateExternalApiEmployeeDto mockRequest = new CreateExternalApiEmployeeDto();
        mockRequest.setName("abc");
        mockRequest.setAge(12);
        mockRequest.setSalary(111111);

        final ExternalApiCreateEmployeeResponseDto mockResponse = new ExternalApiCreateEmployeeResponseDto();
        mockResponse.setStatus("some status");


        when(service.createEmployee(any())).thenReturn(mockResponse);
        assertThat(externalRestApiController.createPost(mockRequest).getBody()).isEqualTo(mockResponse);

    }
}