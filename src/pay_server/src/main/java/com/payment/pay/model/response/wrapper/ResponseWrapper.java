package com.payment.pay.model.response.wrapper;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseWrapper<T> {

    private int code;
    private String message;
    private T body;

    public ResponseWrapper(StatusCode status) {
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public ResponseWrapper(StatusCode status, T body) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.body = body;
    }

}
