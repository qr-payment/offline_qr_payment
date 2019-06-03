package com.payment.merchant.service;

public interface OrderService {

    String reserve(String encodedOrderName, int amount, int count);

    void payment(String redisKey, String reserveId);

}
