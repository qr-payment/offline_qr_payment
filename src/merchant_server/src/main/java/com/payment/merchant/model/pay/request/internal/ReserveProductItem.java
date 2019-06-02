package com.payment.merchant.model.pay.request.internal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReserveProductItem {

    private int count;
    private int price;
    private String productName;
}
