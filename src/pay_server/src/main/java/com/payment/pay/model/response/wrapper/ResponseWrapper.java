package com.payment.pay.model.response.wrapper;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
