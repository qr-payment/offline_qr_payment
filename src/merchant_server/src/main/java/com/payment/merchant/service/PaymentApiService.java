package com.payment.merchant.service;

import com.payment.merchant.model.Order;
import com.payment.merchant.model.pay.response.ApproveResponse;
import com.payment.merchant.model.pay.response.CancelResponse;

public interface PaymentApiService {

    Long paymentReserve(Order order);
    ApproveResponse paymentApprove(String payId);
    CancelResponse paymentCancel(String payId);

}
