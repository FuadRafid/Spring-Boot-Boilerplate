package com.fuadrafid.springboot.service;


import com.fuadrafid.springboot.dto.request.CreateExternalApiEmployeeDto;
import com.fuadrafid.springboot.dto.response.externalapi.createemployee.ExternalApiCreateEmployeeResponseDto;
import com.fuadrafid.springboot.dto.response.externalapi.getemployee.ExternalApiGetEmployeeReponseDto;

public interface ExternalRestApiService {
    ExternalApiGetEmployeeReponseDto getEmployees();

    ExternalApiCreateEmployeeResponseDto createEmployee(CreateExternalApiEmployeeDto requestDto);
}
