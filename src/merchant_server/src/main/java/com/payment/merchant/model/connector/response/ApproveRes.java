package com.payment.merchant.model.connector.response;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApproveRes {

    private long payId;
    private String productName;
    private Long merchantId;
    private String paymentMethod;
    private String cardNo;
    private Timestamp admissionYmdt;
    private int totalPayMount;

}
