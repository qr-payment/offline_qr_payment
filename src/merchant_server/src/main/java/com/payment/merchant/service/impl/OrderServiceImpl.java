package com.payment.merchant.service.impl;

import com.payment.merchant.connector.PayApiConnector;
import com.payment.merchant.exception.InvalidHeaderException;
import com.payment.merchant.exception.NotMatchOrderException;
import com.payment.merchant.model.Redis.Order;
import com.payment.merchant.model.connector.request.Approve;
import com.payment.merchant.model.connector.request.Temporary;
import com.payment.merchant.model.connector.response.ReserveRes;
import com.payment.merchant.model.connector.response.TemporaryRes;
import com.payment.merchant.model.request.Payment;
import com.payment.merchant.model.response.QRScanRes;
import com.payment.merchant.model.wrapper.ResponseWrapper;
import com.payment.merchant.service.OrderService;
import com.payment.merchant.service.RedisService;
import com.payment.merchant.util.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    public Base64Util base64Util = new Base64Util();

    @Autowired
    private PayApiConnector payApiConnector;

    @Autowired
    private RedisService redisService;

    @Value("${url.return}")
    private String baseUrl;

    @Value("${merchant.name}")
    private String merchantName;

    @Value("${merchant.id}")
    private Long merchantId;

    @Override
    public QRScanRes reserve(String encodedOrderName, int amount, int count, Long userIdx) {

        if(userIdx == null)
            throw new InvalidHeaderException();

        String decodedOrderName = base64Util.decode(encodedOrderName);
        String redisId = redisService.createId();
        String returnUrl = baseUrl + "?redisKey=" + redisId + "&reserveId=";

        Order order = Order.builder()
                .orderName(decodedOrderName)
                .amount(amount)
                .count(count)
                .userIdx(userIdx)
                .merchantId(merchantId)
                .build();

        ReserveRes reserveRes = payApiConnector.orderReserve(order);

        order.setReservedId(reserveRes.getReserveId());
        order.setReserveAt(System.currentTimeMillis() / 1000L);
        order.setReturnUrl(returnUrl + reserveRes.getReserveId() + "L");
        redisService.insertOrder(order);

        QRScanRes qrScanRes = QRScanRes.builder()
                .amount(amount)
                .merchantName(merchantName)
                .productName(decodedOrderName)
                .url(order.getReturnUrl())
                .count(count)
                .build();

        return qrScanRes;

    }

    @Override
    public ResponseWrapper payment(Payment payment, String redisKey, Long reserveId, Long userIdx) {

        if(reserveId == null || userIdx == null)
            throw new InvalidHeaderException();

        payment.setReserveId(reserveId);
        payment.setUserIdx(userIdx);

        Temporary temporary = payment.toTemporary();

        TemporaryRes temporaryRes = payApiConnector.orderTemporary(temporary);
        redisService.updateOrder(redisKey, temporaryRes);

        Order order = redisService.getOrder(redisKey);
        if(!order.isSameOrder(temporaryRes))
            throw new NotMatchOrderException();

        Approve approve = Approve.builder()
                .payId(temporaryRes.getPayId())
                .build();

        payApiConnector.orderApprove(approve);

        redisService.updateApproveAt(redisKey);

        ResponseWrapper response = ResponseWrapper.builder()
                .code(0)
                .message("성공")
                .build();

        return response;

    }

}
