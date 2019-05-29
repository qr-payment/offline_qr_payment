package com.payment.auth.model.response.wrapper;

public enum StatusCode {

    OK(0, "Success"),
    ALREADY_EXIST_ID(100, "이미 존재하는 사용자 ID 입니다."),
    INVALID_ID(101, "올바른 사용자 ID 를 입력해 주세요."),
    INVALID_PASSWORD(102, "올바른 비밀번호를 입력해 주세요.");


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
