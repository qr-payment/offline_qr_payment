package com.payment.auth.service.impl;

import com.payment.auth.database.PaymentMethodMapper;
import com.payment.auth.exception.InvalidDataException;
import com.payment.auth.model.request.RegistPayMethod;
import com.payment.auth.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethidServiceImpl implements PaymentMethodService {

    @Autowired
    private PaymentMethodMapper paymentMethodMapper;

    @Override
    public void registPayMethod(RegistPayMethod registPayMethod) {

        switch (registPayMethod.getPaymentMethodType()) {
            case "Account":
                paymentMethodMapper.registAccount(registPayMethod);
                break;
            case "Card":
                paymentMethodMapper.registCard(registPayMethod);
                break;
            default:
                throw new InvalidDataException();
        }

        paymentMethodMapper.registPaymentMethod(registPayMethod);

    }

}
