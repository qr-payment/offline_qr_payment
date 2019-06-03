package com.payment.merchant.connector.impl;

import com.payment.merchant.connector.PayApiConnector;
import com.payment.merchant.exception.PayServerException;
import com.payment.merchant.model.Order;
import com.payment.merchant.model.connector.request.Reserve;
import com.payment.merchant.model.connector.response.ApproveRes;
import com.payment.merchant.model.connector.response.CancelRes;
import com.payment.merchant.model.connector.response.wrapper.PayApiResWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class PayApiConnectorImpl implements PayApiConnector {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.pay.api}")
    private String apiUrl;

    @Override
    public Long orderReserve(Order order) {

        HttpHeaders headers = createHeader();
        Reserve requestBody = Reserve.builder()
                .amount(order.getAmount())
                .count(order.getCount())
                .productName(order.getOrderName())
                .returnUrl(order.getReturnUrl())
                .build();

        HttpEntity<Reserve> request = new HttpEntity<>(requestBody, headers);
        PayApiResWrapper<Long> result = null;

        try {
            result = restTemplate.postForObject(apiUrl + "/payment/reserve", request, PayApiResWrapper.class);
            if (result.getCode() == 0) {
                return result.getBody();
            }
        } catch (Exception e) {
            throw new PayServerException();
        }

        return null;

    }

    @Override
    public ApproveRes orderApprove(String payId) {
        return null;
    }

    @Override
    public CancelRes orderCancel(String payId) {
        return null;
    }

    private HttpHeaders createHeader() {

        HttpHeaders headers = new HttpHeaders();

        headers.add("client_id", "test");
        headers.add("client_secret", "test");

        return headers;

    }

}
