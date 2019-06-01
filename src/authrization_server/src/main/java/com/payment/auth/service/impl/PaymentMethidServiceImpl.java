package com.payment.auth.service.impl;

import com.payment.auth.database.PaymentMethodMapper;
import com.payment.auth.database.UserMapper;
import com.payment.auth.exception.AlreadyExistMethodException;
import com.payment.auth.exception.InvalidDataException;
import com.payment.auth.exception.InvalidPaymentMethodTypeException;
import com.payment.auth.model.request.RegistPayMethod;
import com.payment.auth.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethidServiceImpl implements PaymentMethodService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PaymentMethodMapper paymentMethodMapper;

    @Override
    public void registPayMethod(RegistPayMethod registPayMethod) {

        try {
            switch (registPayMethod.getPaymentMethodType()) {
                case "Account":
                    break;
                case "Card":
                    break;
                default:
                    throw new InvalidPaymentMethodTypeException();
            }

            paymentMethodMapper.registPaymentMethod(registPayMethod);

        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistMethodException();
        }

    }

}
