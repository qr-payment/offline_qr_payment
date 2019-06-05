package com.payment.pay.enums;

import lombok.Getter;

@Getter
public enum TxnStatus {

    Reserve("Reserve"),
    Temporary("Temporary"),
    Approve("Approve"),
    Cancel("Cancel");

    private String status;

    TxnStatus(String status) {
        this.status = status;
    }

}
