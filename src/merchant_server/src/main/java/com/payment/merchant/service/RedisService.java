package com.payment.merchant.service;

import com.payment.merchant.model.Redis.Order;
import com.payment.merchant.model.connector.response.CancelRes;
import com.payment.merchant.model.connector.response.TemporaryRes;

public interface RedisService {

    String createId();

    void insertOrder(Order order);

    Order getOrder(String redisId);

    void updateOrder(String redisId, TemporaryRes response);

    void updateApproveAt(String redisId);

    void updateOrder(String redisId, CancelRes response);


}
