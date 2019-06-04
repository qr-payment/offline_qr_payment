package com.payment.pay;

public enum EndPoint {

    Reserve("/pay/reserve");

    String endPoint;

    EndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

}
