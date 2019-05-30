package com.payment.auth.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;

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
    @ApiModelProperty(hidden = true)
    private String salt;

}
