package com.fuadrafid.springboot.dto.response.math;

public class MathResponseDto {
    private String answer;

    public MathResponseDto(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
