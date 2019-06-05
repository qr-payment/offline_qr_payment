package com.payment.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User implements Serializable {
    private String id;
    private String password;
    private String name;
    private String transactionPw;
}
