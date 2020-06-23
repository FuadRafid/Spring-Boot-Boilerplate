package com.fuadrafid.springboot.controller;


import com.fuadrafid.springboot.dto.request.CreateExternalApiEmployeeDto;
import com.fuadrafid.springboot.dto.response.externalapi.createemployee.ExternalApiCreateEmployeeResponseDto;
import com.fuadrafid.springboot.dto.response.externalapi.getemployee.ExternalApiGetEmployeeReponseDto;
import com.fuadrafid.springboot.service.ExternalRestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/external")
public class ExternalRestApiController {

    @Autowired
    @Qualifier(value = "defaultExternalApiService")
    private ExternalRestApiService service;


    @GetMapping(value = "/employee")
    public ResponseEntity<ExternalApiGetEmployeeReponseDto> getPosts() {
        var data = service.getEmployees();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping(value = "/employee")
    public ResponseEntity<ExternalApiCreateEmployeeResponseDto> createPost(@Valid @RequestBody CreateExternalApiEmployeeDto requestDto) {
        var data = service.createEmployee(requestDto);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
