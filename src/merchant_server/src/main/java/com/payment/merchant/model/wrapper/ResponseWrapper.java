package com.payment.merchant.model.wrapper;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    public ResponseWrapper(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
