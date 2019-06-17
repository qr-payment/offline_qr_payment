package com.payment.merchant.connector.impl;

import com.payment.merchant.connector.PayApiConnector;
import com.payment.merchant.exception.PayServerException;
import com.payment.merchant.model.Redis.Order;
import com.payment.merchant.model.connector.request.Approve;
import com.payment.merchant.model.connector.request.Reserve;
import com.payment.merchant.model.connector.request.Temporary;
import com.payment.merchant.model.connector.response.CancelRes;
import com.payment.merchant.model.connector.response.ReserveRes;
import com.payment.merchant.model.connector.response.TemporaryRes;
import com.payment.merchant.model.connector.response.wrapper.PayApiResWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
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
    public ReserveRes orderReserve(Order order) {

        HttpHeaders headers = createHeader();

        Reserve requestBody = Reserve.builder()
                .amount(order.getAmount())
                .count(order.getCount())
                .productName(order.getOrderName())
                .userIdx(order.getUserIdx())
                .build();

        HttpEntity<Reserve> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<PayApiResWrapper<ReserveRes>> responseEntity = restTemplate.exchange(apiUrl + "/pay/reserve",
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<PayApiResWrapper<ReserveRes>>() {});
        PayApiResWrapper<ReserveRes> result = responseEntity.getBody();

        if(result == null) {
            new PayServerException(result.getCode(), result.getMessage());
        } else if (result.getCode() == 0) {
            return result.getBody();
        }
        new PayServerException(result.getCode(), result.getMessage());
        return null;
    }

    @Override
    public void orderApprove(Approve approve) {

        HttpHeaders headers = createHeader();

        HttpEntity<Approve> request = new HttpEntity<>(approve, headers);
        PayApiResWrapper result = null;

        ResponseEntity<PayApiResWrapper> responseEntity = restTemplate.exchange(apiUrl + "/pay/approve",
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<PayApiResWrapper>() {});

        result = responseEntity.getBody();

        if(result == null || result.getCode() != 0) {
            throw new PayServerException(result.getCode(), result.getMessage());
        }

    }

    @Override
    public TemporaryRes orderTemporary(Temporary temporary) {

        HttpHeaders headers = createHeader();

        HttpEntity<Temporary> request = new HttpEntity<>(temporary, headers);

        ResponseEntity<PayApiResWrapper<TemporaryRes>> responseEntity = restTemplate.exchange(apiUrl + "/pay/temporary",
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<PayApiResWrapper<TemporaryRes>>() {});

        PayApiResWrapper<TemporaryRes> result = responseEntity.getBody();

        if(result == null) {
            throw new PayServerException(result.getCode(), result.getMessage());
        } else if (result.getCode() == 0) {
            return result.getBody();
        }
        throw new PayServerException(result.getCode(), result.getMessage());

    }

    @Override
    public CancelRes orderCancel(String payId) {
        return null;
    }

    private HttpHeaders createHeader() {

        HttpHeaders headers = new HttpHeaders();

        headers.add("client_id", "test");
        headers.add("client_secret", "test");
        headers.add("merchant_id", "0");

        return headers;

    }

}
