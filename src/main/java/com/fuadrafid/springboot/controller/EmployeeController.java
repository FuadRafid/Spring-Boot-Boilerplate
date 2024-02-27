package com.fuadrafid.springboot.controller;


import com.fuadrafid.springboot.dto.request.employee.CreateEmployeeDto;
import com.fuadrafid.springboot.dto.response.employee.createemployee.CreateEmployeeResponseDto;
import com.fuadrafid.springboot.dto.response.employee.getemployee.GetEmployeeResponseDto;
import com.fuadrafid.springboot.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    @Autowired
    @Qualifier(value = "defaultEmployeeService")
    private EmployeeService service;


    @GetMapping(value = "/")
    public ResponseEntity<GetEmployeeResponseDto> getEmployees() {
        GetEmployeeResponseDto data = service.getEmployees();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<CreateEmployeeResponseDto> createEmployee(@Valid @RequestBody CreateEmployeeDto requestDto) {
        CreateEmployeeResponseDto data = service.createEmployee(requestDto);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
