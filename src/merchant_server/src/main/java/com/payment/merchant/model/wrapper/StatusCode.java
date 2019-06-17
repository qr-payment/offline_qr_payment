package com.payment.merchant.model.wrapper;

public enum StatusCode {

    OK(0, "SUCCESS"),

    INVALID_DATA(100, "INVALID_DATA"),

    PAY_SERVER_ERROR(300, "PAY_SERVER_ERROR"),
    INVALID_HEADER(301, "INVALID_HEADER"),
    NOT_MATCH_ORDER(302, "NOT_MATCH_ORDER"),

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
