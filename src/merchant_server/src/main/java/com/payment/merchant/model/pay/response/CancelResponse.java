package com.payment.merchant.model.pay.response;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CancelResponse {

    private long payId;
    private String productName;
    private Long merchantId;
    private String paymentMethod;
    private String cardNo;
    private Timestamp admissionYmdt;
    private Timestamp canceledYmdt;
    private int totalPayMount;

}
