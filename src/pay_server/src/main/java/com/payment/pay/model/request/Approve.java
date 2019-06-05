package com.payment.pay.model.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Approve {

    @NotEmpty
    private String payId;

}
