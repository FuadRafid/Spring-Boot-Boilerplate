package com.fuadrafid.springboot.controller;

import com.fuadrafid.springboot.dto.request.MathRequestDto;
import com.fuadrafid.springboot.dto.response.MathResponseDto;
import com.fuadrafid.springboot.dto.response.MessageResponseDto;
import com.fuadrafid.springboot.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/math")
public class MathController {

    @Autowired
    @Qualifier(value = "defaultMathService")
    private MathService service;

    @GetMapping(value = "")
    public ResponseEntity<MessageResponseDto> helloMessage() {
        MessageResponseDto response = new MessageResponseDto("Hello, this is a calculator.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/add")
    public ResponseEntity<MessageResponseDto> addMessage() {
        MessageResponseDto response = new MessageResponseDto("To add, use the url: /calculator/add/{firstNumber}/{secondNumber}.");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping(value = "/add/{firstNumber}/{secondNumber}")
    public ResponseEntity<MathResponseDto> addNumbers(@PathVariable String firstNumber, @PathVariable String secondNumber) {
        String answer = service.addNumbers(firstNumber, secondNumber);
        MathResponseDto response = new MathResponseDto(answer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/divide")
    public ResponseEntity<MathResponseDto> divideNumbers(@Valid @RequestBody MathRequestDto mathRequestDto) {
        String answer = service.divideNumbers(mathRequestDto.getFirstNumber(), mathRequestDto.getSecondNumber());
        MathResponseDto response = new MathResponseDto(answer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/subtract")
    public ResponseEntity<MathResponseDto> subtractNumbers(@RequestParam String firstNumber, @RequestParam String secondNumber) {
        String answer = service.subtractNumbers(firstNumber, secondNumber);
        MathResponseDto response = new MathResponseDto(answer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/multiply", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MathResponseDto> multiplyNumber(@RequestPart String firstNumber, @RequestPart String secondNumber) {
        String answer = service.multiplyNumbers(firstNumber, secondNumber);
        MathResponseDto response = new MathResponseDto(answer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}