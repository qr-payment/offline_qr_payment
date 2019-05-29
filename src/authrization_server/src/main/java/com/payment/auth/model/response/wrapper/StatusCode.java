package com.payment.auth.model.response.wrapper;

public enum StatusCode {

    OK(0, "SUCCESS"),
    ALREADY_EXIST_ID(100, "ALREADY_EXIST_ID"),
    INVALID_ID(101, "INVALID_ID"),
    INVALID_PASSWORD(102, "INVALID_PASSWORD");


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
