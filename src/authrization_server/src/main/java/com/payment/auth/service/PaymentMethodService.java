package com.payment.auth.service;

import com.payment.auth.model.request.RegistPayMethod;
import com.payment.auth.model.response.GetPayMethods;

public interface PaymentMethodService {

    void registPayMethod(RegistPayMethod registPayMethod);

    GetPayMethods getPaymentMethodList(Long idx);

}
