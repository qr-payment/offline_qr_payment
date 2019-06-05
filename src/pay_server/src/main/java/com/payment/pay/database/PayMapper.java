package com.payment.pay.database;

import com.payment.pay.model.dao.ReserveDAO;
import com.payment.pay.model.request.Reserve;
import com.payment.pay.model.request.Temporary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PayMapper {

    void insertReserve(Reserve reserve);
    ReserveDAO getReserve(Long reserveId);
    void insertTransaction(Temporary temporary);
    void updateReserveToTemporary(Long reserveId);

}
