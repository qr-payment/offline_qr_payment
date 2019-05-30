package com.payment.auth.model.response;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.SwaggerDefinition;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SwaggerDefinition
@Builder
public class SignInRes {

    private Long userIdx;

}
