package com.payment.merchant.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.merchant.model.Order;
import com.payment.merchant.model.pay.request.ReserveRequest;
import com.payment.merchant.model.pay.request.internal.ReserveProductItem;
import com.payment.merchant.model.pay.response.ApproveResponse;
import com.payment.merchant.model.pay.response.CancelResponse;
import com.payment.merchant.model.pay.response.wrapper.ResponseWrapper;
import com.payment.merchant.service.PaymentApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Slf4j
@Service("paymentApiService")
public class PaymentApiServiceImpl implements PaymentApiService {

    @Value("${app.api.url}")
    private String apiUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Long paymentReserve(Order order) {

        HttpHeaders headers = createHeader();

        ReserveRequest reqParam = ReserveRequest.builder()
                .returnUrl(order.getReturnUrl())
                .totalPrice(order.getAmount())
                .reserveProductItem(ReserveProductItem.builder()
                        .productName(order.getOrderName())
                        .count(order.getCount())
                        .price(order.getAmount())
                        .build())
                .build();

        HttpEntity<ReserveRequest> request = new HttpEntity<>(reqParam, headers);

        ResponseWrapper result = null;

        try {
            result = restTemplate.postForObject(apiUrl + "/payment/reserve", request, ResponseWrapper.class);
        } catch (Exception e) {
            log.info("[PayAPI - Reserve] Server Error");
            return null;
        }

        if("Success".equals(result.getCode())) {
            log.info("[PayAPI - Reserve] Success  Message = " + result.getMessage());
            return new Long((Integer)result.getBody());
        }

        log.info("[PayAPI - Reserve] Fail  Message = " + result.getMessage());

        return null;

    }

    @Override
    public ApproveResponse paymentApprove(String payId) {

        HttpHeaders headers = createHeader();

        HttpEntity<Long> request = new HttpEntity<>(Long.parseLong(payId), headers);

        ResponseWrapper result = null;

        try {
            result = restTemplate.postForObject(apiUrl + "/payment/approve", request, ResponseWrapper.class);
        } catch (Exception e) {
            log.info("[PayAPI - Approval] Server Error");
            return null;
        }

        if("Success".equals(result.getCode())) {
            log.info("[PayAPI - Approval] Success  Message = " + result.getMessage());

            ObjectMapper mapper = new ObjectMapper();
            byte[] json;
            ApproveResponse body = null;

            try {
                json = mapper.writeValueAsBytes(result.getBody());
                body = mapper.readValue(json, ApproveResponse.class);
            } catch (IOException e) {
                e.printStackTrace();
                log.info("[PayAPI - Approval] JsonConvert Error");
            }

            return body;

        }

        log.info("[PayAPI - Approval] Fail  Message = " + result.getMessage());

        return null;

    }

    @Override
    public CancelResponse paymentCancel(String payId) {

        HttpHeaders headers = createHeader();

        HttpEntity<Long> request = new HttpEntity<>(Long.parseLong(payId), headers);

        ResponseWrapper result = null;

        try {
            result = restTemplate.postForObject(apiUrl + "/payment/cancel", request, ResponseWrapper.class);
        } catch (Exception e) {
            log.info("[PayAPI - Cancel] Server Error");
            log.info(e.getMessage());
            return null;
        }

        if("Success".equals(result.getCode())) {
            log.info("[PayAPI - Cancel] Success  Message = " + result.getMessage());

            ObjectMapper mapper = new ObjectMapper();
            byte[] json;
            CancelResponse body = null;

            try {
                json = mapper.writeValueAsBytes(result.getBody());
                body = mapper.readValue(json, CancelResponse.class);
            } catch (IOException e) {
                e.printStackTrace();
                log.info("[PayAPI - Cancel] JsonConvert Error");
            }

            return body;
        }

        log.info("[PayAPI - Cancel] Fail  Message = " + result.getMessage());

        return null;

    }

    private HttpHeaders createHeader() {

        HttpHeaders headers = new HttpHeaders();

        headers.add("client_id", "test");
        headers.add("client_secret", "test");

        return headers;

    }

}
