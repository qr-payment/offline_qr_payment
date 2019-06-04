package com.payment.pay.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class Reserve {

    private long reserveId;
    private long merchantId;

    @NotEmpty
    private Integer amount;

    @NotEmpty
    private Integer count;

    @NotEmpty
    private String productName;

}
