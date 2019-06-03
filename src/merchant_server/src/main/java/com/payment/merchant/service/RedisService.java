package com.payment.merchant.service;

import com.payment.merchant.model.Order;
import com.payment.merchant.model.connector.response.ApproveRes;
import com.payment.merchant.model.connector.response.CancelRes;

public interface RedisService {

    String createId();

    void insertOrder(Order order);

    Order getOrder(String redisId);

    void updateOrder(String redisId, ApproveRes response);

    void updateOrder(String redisId, CancelRes response);


}
