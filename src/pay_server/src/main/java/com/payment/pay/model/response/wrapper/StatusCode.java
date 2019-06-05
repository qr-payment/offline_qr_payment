package com.payment.pay.model.response.wrapper;

import lombok.Getter;

@Getter
public enum StatusCode {

    OK(0, "SUCCESS"),

    INVALID_DATA(100, "INVALID_DATA"),
    EMPTY_HEADER(101, "EMPTY_HEADER"),

    INVALID_RESERVE(200, "INVALID_RESERVE"),
    ALREADY_STATUS_CHANGE(201, "ALREADY_STATUS_CHANGE"),
    NOT_MATCH_RESERVE_INFO(202, "NOT_MATCH_RESERVE_INFO"),
    INVALID_PAY_METHOD(203, "INVALID_PAY_METHOD");

    private int code;
    private String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
