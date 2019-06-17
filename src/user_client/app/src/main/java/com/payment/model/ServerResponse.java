package com.payment.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ServerResponse<T> {
    private int code;
    private String message;
    private T body;
}
