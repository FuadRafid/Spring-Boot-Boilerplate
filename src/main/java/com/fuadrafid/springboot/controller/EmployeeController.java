package com.fuadrafid.springboot.controller;


import com.fuadrafid.springboot.dto.request.CreateExternalApiEmployeeDto;
import com.fuadrafid.springboot.dto.response.externalapi.createemployee.CreateEmployeeResponseDto;
import com.fuadrafid.springboot.dto.response.externalapi.getemployee.GetEmployeeResponseDto;
import com.fuadrafid.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    @Autowired
    @Qualifier(value = "defaultEmployeeService")
    private EmployeeService service;


    @GetMapping(value = "/")
    public ResponseEntity<GetEmployeeResponseDto> getPosts() {
        var data = service.getEmployees();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<CreateEmployeeResponseDto> createPost(@Valid @RequestBody CreateExternalApiEmployeeDto requestDto) {
        var data = service.createEmployee(requestDto);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
