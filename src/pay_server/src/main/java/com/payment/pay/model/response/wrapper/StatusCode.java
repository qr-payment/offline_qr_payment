package com.payment.pay.model.response.wrapper;

import lombok.Getter;

@Getter
public enum StatusCode {

    OK(0, "SUCCESS"),

    INVALID_DATA(100, "INVALID_DATA"),
    EMPTY_HEADER(101, "EMPTY_HEADER");

    private int code;
    private String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
