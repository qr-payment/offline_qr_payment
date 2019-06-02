package com.payment.merchant.model.pay.response;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApproveResponse {

    private long payId;
    private String productName;
    private Long merchantId;
    private String paymentMethod;
    private String cardNo;
    private Timestamp admissionYmdt;
    private int totalPayMount;

}
