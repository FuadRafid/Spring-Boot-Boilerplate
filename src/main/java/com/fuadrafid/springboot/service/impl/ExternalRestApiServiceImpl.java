package com.fuadrafid.springboot.service.impl;

import com.fuadrafid.springboot.dto.request.CreateExternalApiEmployeeDto;
import com.fuadrafid.springboot.dto.response.externalapi.createemployee.ExternalApiCreateEmployeeResponseDto;
import com.fuadrafid.springboot.dto.response.externalapi.getemployee.ExternalApiGetEmployeeReponseDto;
import com.fuadrafid.springboot.exception.ExceptionCode;
import com.fuadrafid.springboot.exception.ServiceException;
import com.fuadrafid.springboot.service.ExternalRestApiService;
import com.fuadrafid.springboot.util.RestRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;

@Service(value = "defaultExternalApiService")
public class ExternalRestApiServiceImpl implements ExternalRestApiService {

    @Autowired
    RestRequestUtil restRequestUtil;

    @Override
    public ExternalApiGetEmployeeReponseDto getEmployees() {
        ClientResponse response = restRequestUtil.getSync("http://dummy.restapiexample.com/api/v1/employees");
        if (response.statusCode() != HttpStatus.OK)
            throw new ServiceException("Failed to get employee data, error response from server", ExceptionCode.ERROR_FETCHING_EMPLOYEE_DATA);
        return response.bodyToMono(ExternalApiGetEmployeeReponseDto.class).block();

    }

    @Override
    public ExternalApiCreateEmployeeResponseDto createEmployee(CreateExternalApiEmployeeDto requestDto) {
        ClientResponse response = restRequestUtil.postSync("http://dummy.restapiexample.com/api/v1/create", requestDto, CreateExternalApiEmployeeDto.class);
        if (response.statusCode() != HttpStatus.OK)
            throw new ServiceException("Failed to create employee, error response from server", ExceptionCode.ERROR_CREATING_EMPLOYEE);
        return response.bodyToMono(ExternalApiCreateEmployeeResponseDto.class).block();
    }
}
