package com.payment.auth.database;

import com.payment.auth.model.request.RegistPayMethod;
import com.payment.auth.model.response.internal.PayMethod;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PaymentMethodMapper {

    void registPaymentMethod(RegistPayMethod registPayMethod);

    List<PayMethod> getPayMethods(Long userIdx);

}
