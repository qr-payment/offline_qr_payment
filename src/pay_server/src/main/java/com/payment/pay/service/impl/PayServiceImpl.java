package com.payment.pay.service.impl;

import com.payment.pay.database.PayMapper;
import com.payment.pay.model.request.Reserve;
import com.payment.pay.model.response.ReserveRes;
import com.payment.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;

public class PayServiceImpl implements PayService {

    @Autowired
    private PayMapper payMapper;

    @Override
    public ReserveRes reserve(Reserve reserve, long merchantId) {

        reserve.setMerchantId(merchantId);

        payMapper.insertReserve(reserve);

        ReserveRes response = ReserveRes.builder()
                .reserveId(reserve.getReserveId())
                .build();

        return response;

    }

}
