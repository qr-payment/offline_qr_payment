package com.payment.merchant.service.impl;

import com.payment.merchant.connector.PayApiConnector;
import com.payment.merchant.model.Order;
import com.payment.merchant.service.OrderService;
import com.payment.merchant.service.RedisService;
import com.payment.merchant.util.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    public Base64Util base64Util = new Base64Util();

    @Autowired
    private PayApiConnector payApiConnector;

    @Autowired
    private RedisService redisService;

    @Value("${url.return}")
    String baseUrl;

    @Override
    public String reserve(String encodedOrderName, int amount, int count) {

        String decodedOrderName = base64Util.decode(encodedOrderName);
        String redisId = redisService.createId();
        String returnUrl = baseUrl + "?redisKey=" + redisId + "&reserveId=";

        Order order = Order.builder()
                .orderName(decodedOrderName)
                .amount(amount)
                .count(count)
                .build();

        //long reserveId = payApiConnector.orderReserve(order);
        long reserveId = 0;

        order.setReservedId(reserveId);
        order.setReturnUrl(returnUrl + reserveId);
        redisService.insertOrder(order);

        return order.getReturnUrl();

    }

    @Override
    public void payment(String redisKey, String reserveId) {

    }
}
