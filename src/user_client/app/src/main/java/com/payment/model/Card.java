package com.payment.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Card {
    private String paymentMethodNum;
    private String paymentMethodType;
    private String nickName;
    private String bankName;
    private Long userIdx;
}
