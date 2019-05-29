package com.payment.auth.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUp {

    private long userIdx;
    private String id;
    private String password;
    private String name;
    private String transactionPw;

}
