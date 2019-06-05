package com.payment.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Account {
    private String bankName;
    private String nickName;
    private String paymentMethodNum;
    private String paymentMethodType;
    private Long userIdx;
}
