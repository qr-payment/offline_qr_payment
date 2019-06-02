package com.payment.merchant.service;

import com.payment.merchant.model.Order;
import com.payment.merchant.model.pay.response.ApproveResponse;
import com.payment.merchant.model.pay.response.CancelResponse;

public interface RedisService {

    String createId();

    void insertOrder(Order order);

    Order getOrder(String redisId);

    void updateOrder(String redisId, ApproveResponse response);

    void updateOrder(String redisId, CancelResponse response);

}
