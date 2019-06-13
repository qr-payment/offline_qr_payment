package com.payment.merchant.model.connector.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Temporary {

    private int amount;
    private int count;
    private String methodType;
    private String methodNum;
    private String productName;
    private long reserveId;
    private String transactionPw;
    private long userIdx;

}
