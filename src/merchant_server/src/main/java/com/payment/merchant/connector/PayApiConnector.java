package com.payment.merchant.connector;

import com.payment.merchant.model.Order;
import com.payment.merchant.model.connector.response.ApproveRes;
import com.payment.merchant.model.connector.response.CancelRes;

public interface PayApiConnector {

    Long orderReserve(Order order);
    ApproveRes orderApprove(String payId);
    CancelRes orderCancel(String payId);

}
