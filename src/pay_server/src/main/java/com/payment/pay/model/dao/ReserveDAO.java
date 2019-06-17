package com.payment.pay.model.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReserveDAO {

    private Long reserveId;
    private Long merchantId;
    private Long createAt;
    private Integer amount;
    private String productName;
    private Integer count;
    private Long userIdx;
    private String status;

}
