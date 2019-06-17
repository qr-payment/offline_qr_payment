package com.payment.merchant.model.connector.response.wrapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayApiResWrapper<T> {

    private int code;
    private String message;
    private T body;

}
