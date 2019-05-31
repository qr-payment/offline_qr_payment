package com.payment.auth.model.db;

import com.payment.auth.model.response.SignInRes;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    private Long user_idx;
    private String id;
    private String password;
    private String transaction_pw;
    private String name;
    private String salt;

    public SignInRes toSignInRes() {

        return SignInRes.builder()
                .userIdx(this.getUser_idx())
                .build();

    }

}
