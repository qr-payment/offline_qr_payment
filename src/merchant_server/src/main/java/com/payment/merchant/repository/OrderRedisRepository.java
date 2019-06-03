package com.payment.merchant.repository;

import com.payment.merchant.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRedisRepository extends CrudRepository<Order, String> {
}
