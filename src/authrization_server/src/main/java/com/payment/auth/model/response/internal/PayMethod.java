package com.payment.auth.model.response.internal;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayMethod {

    private String paymentMethodType;
    private String paymentMethodNum;
    private String nickName;
    private String bankName;
    private long registAt;

}
