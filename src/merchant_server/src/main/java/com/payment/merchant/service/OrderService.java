package com.payment.merchant.service;

public interface OrderService {

    long reserve(String encodedOrderName, int amount, int count);

    void approval(String redisKey, String payId);

}
