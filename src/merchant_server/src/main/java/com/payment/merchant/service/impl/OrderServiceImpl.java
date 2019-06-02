package com.payment.merchant.service.impl;

import com.payment.merchant.model.Order;
import com.payment.merchant.model.pay.response.ApproveResponse;
import com.payment.merchant.model.pay.response.CancelResponse;
import com.payment.merchant.service.OrderService;
import com.payment.merchant.service.PaymentApiService;
import com.payment.merchant.service.RedisService;
import com.payment.merchant.util.Base64Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    public Base64Util base64Util = new Base64Util();

    @Autowired
    private RedisService redisService;

    @Autowired
    private PaymentApiService paymentApiService;

    @Value("${app.returnUrl}")
    private String returnUrl;

    public long reserve(String encodedOrderName, int amount, int count) {

        String orderName = base64Util.decode(encodedOrderName);
        String redisId = redisService.createId();

        log.info("Order Name = " + orderName);
        log.info("Amount = " + amount);

        Order order = Order.builder()
                .orderName(orderName)
                .amount(amount)
                .id(redisId)
                .count(count)
                .returnUrl(returnUrl + "?redisKey=" + redisId + "&payId=")
                .build();

        long reserveId = paymentApiService.paymentReserve(order);

        order.setReservedId(reserveId);
        redisService.insertOrder(order);

        return reserveId;

    }

    @Override
    public void approval(String redisKey, String payId) {

        ApproveResponse approveResponse = paymentApiService.paymentApprove(payId);

        Order order = redisService.getOrder(redisKey);

        CancelResponse cancelResponse = null;

        if(order != null && approveResponse != null) {
            if(order.getAmount() != approveResponse.getTotalPayMount()
            && order.getOrderName() != approveResponse.getProductName()) {

                cancelResponse = paymentApiService.paymentCancel(payId);

            }
        } else {

            cancelResponse = paymentApiService.paymentCancel(payId);

        }

        if(cancelResponse == null) {
            redisService.updateOrder(redisKey, approveResponse);
        } else {
            redisService.updateOrder(redisKey, cancelResponse);
        }

    }

}
