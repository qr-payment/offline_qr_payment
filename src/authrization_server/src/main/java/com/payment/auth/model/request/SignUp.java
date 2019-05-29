package com.payment.auth.model.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUp {

    private String id;
    private String password;
    private String name;
    private String transactionPw;
    private String salt;

}
