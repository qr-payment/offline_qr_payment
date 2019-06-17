package com.payment.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TransactionResponse {
    private String url;
    private String productName;
    private int amount;
    private String merchantName;
    private int count;
}
