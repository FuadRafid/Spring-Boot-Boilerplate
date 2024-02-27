package com.fuadrafid.springboot.controller;

import com.fuadrafid.springboot.dto.request.math.DivisionRequestDto;
import com.fuadrafid.springboot.dto.response.math.MathResponseDto;
import com.fuadrafid.springboot.service.MathService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/math")
public class MathController {

    private final MathService service;

    public MathController(@Qualifier(value = "defaultMathService") MathService service) {
        this.service = service;
    }


    @GetMapping(value = "/power/{number}/{power}")
    public ResponseEntity<MathResponseDto> power(@PathVariable String number, @PathVariable String power) {
        String answer = service.getPower(number, power);
        MathResponseDto response = new MathResponseDto(answer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/divide")
    public ResponseEntity<MathResponseDto> divideNumbers(@Valid @RequestBody DivisionRequestDto divisionRequestDto) {
        String answer = service.divideNumbers(divisionRequestDto.getDividend(), divisionRequestDto.getDivisor());
        MathResponseDto response = new MathResponseDto(answer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/multiply")
    public ResponseEntity<MathResponseDto> multiplyNumber(@RequestParam String multiplier, @RequestParam String multiplicand) {
        String answer = service.multiplyNumbers(multiplier, multiplicand);
        MathResponseDto response = new MathResponseDto(answer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/exp", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MathResponseDto> exponential(@RequestPart String input) {
        String answer = service.getExponential(input);
        MathResponseDto response = new MathResponseDto(answer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}