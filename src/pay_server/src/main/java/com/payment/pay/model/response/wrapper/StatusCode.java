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
    INVALID_PAY_METHOD(203, "INVALID_PAY_METHOD"),

    INVALID_PAY_ID(300, "INVALID_PAY_ID"),
    ALREADY_PAID(301, "ALREADY_PAID"),
    ALREADY_CANCELLED(302, "ALREADY_CANCELLED"),
    NOT_MATCH_MERCHANT_ID(303, "NOT_MATCH_MERCHANT_ID"),
    NOT_MATCH_TRANSACTION_PW(303, "NOT_MATCH_TRANSACTION_PW"),

    INTERNAL_SERVER_ERROR(400, "INTERNAL_SERVER_ERROR");

    private int code;
    private String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
