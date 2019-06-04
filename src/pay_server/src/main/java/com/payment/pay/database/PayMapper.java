package com.payment.pay.database;

import com.payment.pay.model.request.Reserve;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PayMapper {

    void insertReserve(Reserve reserve);

}
