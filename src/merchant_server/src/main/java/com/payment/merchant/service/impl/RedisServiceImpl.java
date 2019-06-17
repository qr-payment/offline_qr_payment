package com.payment.merchant.service.impl;

import com.payment.merchant.model.Redis.Order;
import com.payment.merchant.model.connector.response.CancelRes;
import com.payment.merchant.model.connector.response.TemporaryRes;
import com.payment.merchant.repository.OrderRedisRepository;
import com.payment.merchant.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Autowired
    private OrderRedisRepository orderRedisRepository;

    @Override
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

        Order order = object.get();

        return order;

    }

    @Override
    public void updateOrder(String redisId, TemporaryRes temporaryRes) {

        Optional<Order> object = orderRedisRepository.findById(redisId);

        Order order = object.get();

        order.setPayId(temporaryRes.getPayId());

        orderRedisRepository.save(order);

    }

    @Override
    public void updateApproveAt(String redisId) {

        Optional<Order> object = orderRedisRepository.findById(redisId);

        Order order = object.get();

        order.setApproveAt(System.currentTimeMillis() / 1000L);

        orderRedisRepository.save(order);

    }

    @Override
    public void updateOrder(String redisId, CancelRes response) {

        Optional<Order> object = orderRedisRepository.findById(redisId);

        Order order = object.get();

        // TODO: Order Setting

        orderRedisRepository.save(order);

    }
}
