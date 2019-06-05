package com.payment.merchant.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QRScanRes {

    private String url;
    private String productName;
    private int amount;
    private String merchantName;

}
