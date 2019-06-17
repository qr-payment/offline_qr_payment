package com.payment.merchant.connector;

import com.payment.merchant.model.Redis.Order;
import com.payment.merchant.model.connector.request.Approve;
import com.payment.merchant.model.connector.request.Temporary;
import com.payment.merchant.model.connector.response.CancelRes;
import com.payment.merchant.model.connector.response.ReserveRes;
import com.payment.merchant.model.connector.response.TemporaryRes;

public interface PayApiConnector {

    ReserveRes orderReserve(Order order);
    void orderApprove(Approve approve);
    CancelRes orderCancel(String payId);
    TemporaryRes orderTemporary(Temporary temporary);

}
