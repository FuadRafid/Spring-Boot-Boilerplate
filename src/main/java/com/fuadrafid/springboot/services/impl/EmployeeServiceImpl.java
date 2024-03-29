package com.fuadrafid.springboot.services.impl;

import com.fuadrafid.springboot.dto.request.employee.CreateEmployeeDto;
import com.fuadrafid.springboot.dto.response.employee.createemployee.CreateEmployeeResponseDto;
import com.fuadrafid.springboot.dto.response.employee.getemployee.GetEmployeeResponseDto;
import com.fuadrafid.springboot.exception.ApplicationInternalException;
import com.fuadrafid.springboot.services.EmployeeService;
import com.fuadrafid.springboot.util.RestRequestUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;

@Service(value = "defaultEmployeeService")
public class EmployeeServiceImpl implements EmployeeService {

    final RestRequestUtil restRequestUtil;

    public EmployeeServiceImpl(RestRequestUtil restRequestUtil) {
        this.restRequestUtil = restRequestUtil;
    }

    @Override
    public GetEmployeeResponseDto getEmployees() {
        ClientResponse response = restRequestUtil.getSync("http://dummy.restapiexample.com/api/v1/employees");
        if (response.statusCode() != HttpStatus.OK)
            throw new ApplicationInternalException("Failed to get employee data, error response from employee server", HttpStatus.FAILED_DEPENDENCY);
        return response.bodyToMono(GetEmployeeResponseDto.class).block();

    }

    @Override
    public CreateEmployeeResponseDto createEmployee(CreateEmployeeDto requestDto) {
        ClientResponse response = restRequestUtil.postSync("http://dummy.restapiexample.com/api/v1/create", requestDto, CreateEmployeeDto.class);
        if (response.statusCode() != HttpStatus.OK)
            throw new ApplicationInternalException("Failed to create employee, error response from employee server", HttpStatus.FAILED_DEPENDENCY);
        return response.bodyToMono(CreateEmployeeResponseDto.class).block();
    }
}
