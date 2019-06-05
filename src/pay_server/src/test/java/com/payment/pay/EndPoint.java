package com.payment.pay;

public enum EndPoint {

    Reserve("/pay/reserve"),
    Temporary("/pay/temporary");

    String endPoint;

    EndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

}
