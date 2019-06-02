package com.payment.merchant.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@RedisHash("order")
public class Order implements Serializable {

    @Id
    private String id;
    private String orderName;
    private int amount;
    private int count;
    private long reservedId;
    private String returnUrl;
    private long payId;
    private Long merchantId;
    private String paymentMethod;
    private String cardNo;
    private Timestamp admissionYmdt;
    private Timestamp cancelledYmdt;

}
