package com.payment.merchant.model.pay.response.wrapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseWrapper<T> {

    private String code;
    private String message;
    private T body;

}
