package com.fuadrafid.springboot.dto.response.externalapi.createemployee;

public class CreateEmployeeResponseDto {
    private CreateEmployeeResponseData data;

    private String status;

    public CreateEmployeeResponseData getData() {
        return data;
    }

    public void setData(CreateEmployeeResponseData data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
