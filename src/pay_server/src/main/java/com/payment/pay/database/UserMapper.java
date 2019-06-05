package com.payment.pay.database;

import com.payment.pay.model.request.Temporary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    int userPaymentMethodCheck(Temporary temporary);

}
