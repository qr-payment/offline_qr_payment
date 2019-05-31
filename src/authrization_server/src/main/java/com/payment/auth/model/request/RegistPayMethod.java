package com.payment.auth.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistPayMethod {

    @ApiModelProperty(hidden = true)
    private long paymentIdx;

    @NotNull
    private long userIdx;

    @NotEmpty
    private String paymentMethodType;

    @NotEmpty
    private String paymentNum;

}
