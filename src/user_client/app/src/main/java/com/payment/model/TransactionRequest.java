package com.payment.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionRequest {
    private int amount;
    private int count;
    private String methodType;
    private String methodNum;
    private String productName;
    private String transactionPw;
}
