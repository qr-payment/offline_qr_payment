package com.payment.auth.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignIn {

    private String id;
    private String password;
    private String name;
    private String transaction_pw;

}
