package com.payment.pay.service.impl;

import com.payment.pay.database.PayMapper;
import com.payment.pay.exception.EmptyHeaderException;
import com.payment.pay.exception.InvalidDataException;
import com.payment.pay.model.request.Reserve;
import com.payment.pay.model.response.ReserveRes;
import com.payment.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PayMapper payMapper;

    @Override
    public ReserveRes reserve(Reserve reserve, Long merchantId) {

        if(merchantId == null)
            throw new EmptyHeaderException();

        reserve.setMerchantId(merchantId);

        payMapper.insertReserve(reserve);

        ReserveRes response = ReserveRes.builder()
                .reserveId(reserve.getReserveId())
                .build();

        return response;

    }

}
