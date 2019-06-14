package com.payment.merchant.model.Redis;


import com.payment.merchant.model.connector.request.Temporary;
import com.payment.merchant.model.connector.response.TemporaryRes;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@RedisHash("order")
public class Order implements Serializable {

    @Id
    private String id; //
    private String orderName; //
    private int amount; //
    private int count; //
    private long reservedId; //
    private String payId; //
    private long merchantId; //
    private String paymentMethod;
    private String paymentNum;
    private String returnUrl; //
    private long reserveAt; //
    private long approveAt;
    private long cancelledAt;
    private long userIdx; //

    public boolean isSameOrder(TemporaryRes temporaryRes) {
        return reservedId == temporaryRes.getReserveId() &&
                orderName.equals(temporaryRes.getProductName()) &&
                amount == temporaryRes.getAmount() &&
                count == temporaryRes.getCount() &&
                paymentMethod.equals(temporaryRes.getMethodType()) &&
                paymentNum.equals(temporaryRes.getMethodNum()) &&
                userIdx == temporaryRes.getUserIdx() &&
                merchantId == temporaryRes.getMerchantId();
    }

}
