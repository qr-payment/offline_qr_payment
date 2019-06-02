package com.payment.merchant.service.impl;

import com.payment.merchant.model.Order;
import com.payment.merchant.model.pay.response.ApproveResponse;
import com.payment.merchant.model.pay.response.CancelResponse;
import com.payment.merchant.repository.OrderRedisRepository;
import com.payment.merchant.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private OrderRedisRepository orderRedisRepository;

    public String createId() {

        String id = null;

        //randumUUID 는 거의 중복될 확률이 희박하다.
        do {
            id = UUID.randomUUID().toString();
        } while(orderRedisRepository.findById(id).isPresent());

        log.info("Create Redis ID : " + id);

        return id;

    }

    @Override
    public void insertOrder(Order order) {

        orderRedisRepository.save(order);

    }

    @Override
    public Order getOrder(String redisId) {

        Optional<Order> object = orderRedisRepository.findById(redisId);

        return object.orElse(null);

    }

    @Override
    public void updateOrder(String redisId, ApproveResponse response) {

        Optional<Order> object = orderRedisRepository.findById(redisId);

        Order order = object.get();

        order.setAdmissionYmdt(response.getAdmissionYmdt());
        order.setCardNo(response.getCardNo());
        order.setMerchantId(response.getMerchantId());
        order.setPaymentMethod(response.getPaymentMethod());

        orderRedisRepository.save(order);

    }

    @Override
    public void updateOrder(String redisId, CancelResponse response) {

        Optional<Order> object = orderRedisRepository.findById(redisId);

        Order order = object.get();

        order.setAdmissionYmdt(response.getAdmissionYmdt());
        order.setCardNo(response.getCardNo());
        order.setMerchantId(response.getMerchantId());
        order.setPaymentMethod(response.getPaymentMethod());
        order.setCancelledYmdt(response.getCanceledYmdt());

        orderRedisRepository.save(order);

    }
}
