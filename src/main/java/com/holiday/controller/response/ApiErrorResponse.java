package com.holiday.controller.response;

public class ApiErrorResponse {

    private Integer status;
    private Integer code;
    private String message;

    public ApiErrorResponse(Integer status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
