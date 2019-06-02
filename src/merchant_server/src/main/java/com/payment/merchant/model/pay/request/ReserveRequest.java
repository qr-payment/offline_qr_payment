package com.payment.merchant.model.pay.request;

import com.payment.merchant.model.pay.request.internal.ReserveProductItem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReserveRequest {

    private int totalPrice;
    private String returnUrl;
    private ReserveProductItem reserveProductItem;

}
