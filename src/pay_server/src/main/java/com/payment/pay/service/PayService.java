package com.payment.pay.service;

import com.payment.pay.model.request.Reserve;
import com.payment.pay.model.request.Temporary;
import com.payment.pay.model.response.ReserveRes;
import com.payment.pay.model.response.TemporaryRes;

public interface PayService {

    ReserveRes reserve(Reserve reserve, Long merchantId);
    TemporaryRes temporary(Temporary temporary, Long merchantId);

}
