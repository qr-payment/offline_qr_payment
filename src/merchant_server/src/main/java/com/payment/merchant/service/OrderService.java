package com.payment.merchant.service;

import com.payment.merchant.model.response.QRScanRes;

public interface OrderService {

    QRScanRes reserve(String encodedOrderName, int amount, int count);

    void payment(String redisKey, String reserveId);

}
