package com.payment.merchant.service;

import com.payment.merchant.model.request.Payment;
import com.payment.merchant.model.response.QRScanRes;

public interface OrderService {

    QRScanRes reserve(String encodedOrderName, int amount, int count, Long userIdx);

    void payment(Payment payment, String redisKey, Long reserveId, Long userIdx);

}
