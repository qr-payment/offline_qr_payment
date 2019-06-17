package com.payment.merchant.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class PayServerException extends RuntimeException {

    private int code;
    private String message;

}
