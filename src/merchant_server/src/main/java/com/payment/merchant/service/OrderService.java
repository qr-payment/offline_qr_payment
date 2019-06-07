package com.payment.merchant.service;

import com.payment.merchant.model.request.Payment;
import com.payment.merchant.model.response.QRScanRes;
import com.payment.merchant.model.wrapper.ResponseWrapper;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    QRScanRes reserve(String encodedOrderName, int amount, int count, Long userIdx);

    ResponseWrapper payment(Payment payment, String redisKey, Long reserveId, Long userIdx);

}
