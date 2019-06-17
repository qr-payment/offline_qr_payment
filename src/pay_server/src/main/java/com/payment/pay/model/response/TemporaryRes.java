package com.payment.pay.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TemporaryRes {

    private String payId;
    
    private long reserveId;
    private String productName;
    private int amount;
    private int count;
    private String methodType;
    private String methodNum;
    private long userIdx;
    private long merchantId;
    
}
