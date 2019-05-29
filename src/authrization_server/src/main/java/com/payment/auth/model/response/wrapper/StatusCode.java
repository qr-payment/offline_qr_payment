package com.payment.auth.model.response.wrapper;

public enum StatusCode {

    OK(0, "SUCCESS"),
    INVALID_DATA(100, "INVALID_DATA"),
    ALREADY_EXIST_ID(200, "ALREADY_EXIST_ID"),
    INVALID_ID(201, "INVALID_ID"),
    INVALID_PASSWORD(202, "INVALID_PASSWORD"),
    INTERNAL_SERVER_ERROR(400, "INTERNAL_SERVER_ERROR");


    private int code;
    private String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

}
