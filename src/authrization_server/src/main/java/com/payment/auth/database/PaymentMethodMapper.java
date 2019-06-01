package com.payment.auth.database;

import com.payment.auth.model.request.RegistPayMethod;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PaymentMethodMapper {

    void registPaymentMethod(RegistPayMethod registPayMethod);

}
