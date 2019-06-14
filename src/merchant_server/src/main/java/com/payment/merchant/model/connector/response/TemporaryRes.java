package com.payment.merchant.model.connector.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
