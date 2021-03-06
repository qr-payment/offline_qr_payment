package com.payment.auth.model.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignIn {

    @NotEmpty
    private String id;

    @NotEmpty
    private String password;

}
