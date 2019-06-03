package com.payment.merchant.model.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWrapper<T> {

    private int code;
    private String message;
    private T body;

    public ResponseWrapper(StatusCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public ResponseWrapper(StatusCode code, T body) {
        this.code = code.getCode();
        this.message = code.getMessage();
        this.body = body;
    }

}
