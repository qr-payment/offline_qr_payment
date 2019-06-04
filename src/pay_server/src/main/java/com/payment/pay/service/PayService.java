package com.payment.pay.service;

import com.payment.pay.model.request.Reserve;
import com.payment.pay.model.response.ReserveRes;

public interface PayService {

    ReserveRes reserve(Reserve reserve, Long merchantId);

}
